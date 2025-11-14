import { useState, useEffect } from 'react';
import {
  Box,
  Container,
  Heading,
  Button,
  SimpleGrid,
  useDisclosure,
  useToast,
  Spinner,
  Center,
  Text,
  HStack,
  IconButton,
  useColorMode,
} from '@chakra-ui/react';
import { AddIcon, MoonIcon, SunIcon } from '@chakra-ui/icons';
import { printerApi } from '../services/api';
import { Printer } from '../types';
import PrinterCard from '../components/PrinterCard';
import PrinterForm from '../components/PrinterForm';

const HomePage = () => {
  const [printers, setPrinters] = useState<Printer[]>([]);
  const [selectedPrinter, setSelectedPrinter] = useState<Printer | undefined>();
  const [isLoading, setIsLoading] = useState(true);
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { colorMode, toggleColorMode } = useColorMode();
  const toast = useToast();

  const fetchPrinters = async () => {
    try {
      setIsLoading(true);
      const response = await printerApi.getAll();
      setPrinters(response.data);
    } catch (error) {
      toast({
        title: 'Error',
        description: 'Failed to fetch printers.',
        status: 'error',
        duration: 3000,
        isClosable: true,
      });
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchPrinters();
  }, []);

  const handleAddPrinter = () => {
    setSelectedPrinter(undefined);
    onOpen();
  };

  const handleEditPrinter = (id: number) => {
    const printer = printers.find((p) => p.id === id);
    if (printer) {
      setSelectedPrinter(printer);
      onOpen();
    }
  };

  const handleViewPrinter = (id: number) => {
    const printer = printers.find((p) => p.id === id);
    if (printer) {
      toast({
        title: 'Printer Details',
        description: `${printer.manufacturer} - ${printer.type}`,
        status: 'info',
        duration: 3000,
        isClosable: true,
      });
    }
  };

  const handleDeletePrinter = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this printer?')) {
      try {
        await printerApi.delete(id);
        toast({
          title: 'Printer deleted',
          description: 'The printer has been successfully deleted.',
          status: 'success',
          duration: 3000,
          isClosable: true,
        });
        fetchPrinters();
      } catch (error) {
        toast({
          title: 'Error',
          description: 'Failed to delete printer.',
          status: 'error',
          duration: 3000,
          isClosable: true,
        });
      }
    }
  };

  return (
    <Box minH="100vh" py={8}>
      <Container maxW="container.xl">
        <HStack justify="space-between" mb={8}>
          <Heading size="xl">Printer Manager</Heading>
          <HStack>
            <IconButton
              aria-label="Toggle color mode"
              icon={colorMode === 'light' ? <MoonIcon /> : <SunIcon />}
              onClick={toggleColorMode}
            />
            <Button
              leftIcon={<AddIcon />}
              colorScheme="blue"
              onClick={handleAddPrinter}
            >
              Add Printer
            </Button>
          </HStack>
        </HStack>

        {isLoading ? (
          <Center py={20}>
            <Spinner size="xl" />
          </Center>
        ) : printers.length === 0 ? (
          <Center py={20}>
            <Box textAlign="center">
              <Text fontSize="xl" mb={4}>
                No printers found
              </Text>
              <Button
                leftIcon={<AddIcon />}
                colorScheme="blue"
                onClick={handleAddPrinter}
              >
                Add Your First Printer
              </Button>
            </Box>
          </Center>
        ) : (
          <SimpleGrid columns={{ base: 1, md: 2, lg: 3 }} spacing={6}>
            {printers.map((printer) => (
              <PrinterCard
                key={printer.id}
                printer={printer}
                onView={handleViewPrinter}
                onEdit={handleEditPrinter}
                onDelete={handleDeletePrinter}
              />
            ))}
          </SimpleGrid>
        )}

        <PrinterForm
          isOpen={isOpen}
          onClose={onClose}
          printer={selectedPrinter}
          onSuccess={fetchPrinters}
        />
      </Container>
    </Box>
  );
};

export default HomePage;
