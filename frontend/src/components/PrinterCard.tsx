import {
  Box,
  Card,
  CardBody,
  Heading,
  Text,
  Stack,
  IconButton,
  useColorModeValue,
  HStack,
  Badge,
} from '@chakra-ui/react';
import { DeleteIcon, EditIcon, ViewIcon } from '@chakra-ui/icons';
import { Printer } from '../types';

interface PrinterCardProps {
  printer: Printer;
  onView: (id: number) => void;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}

const PrinterCard = ({ printer, onView, onEdit, onDelete }: PrinterCardProps) => {
  const cardBg = useColorModeValue('white', 'gray.700');
  const borderColor = useColorModeValue('gray.200', 'gray.600');

  return (
    <Card
      bg={cardBg}
      borderWidth="1px"
      borderColor={borderColor}
      borderRadius="lg"
      overflow="hidden"
      transition="all 0.3s"
      _hover={{
        transform: 'translateY(-4px)',
        shadow: 'lg',
      }}
    >
      <CardBody>
        <Stack spacing={3}>
          <HStack justify="space-between">
            <Heading size="md" noOfLines={1}>
              {printer.manufacturer}
            </Heading>
            <Badge colorScheme="green">Active</Badge>
          </HStack>

          <Box>
            <Text fontSize="sm" color="gray.500">
              Model
            </Text>
            <Text fontWeight="medium">{printer.type}</Text>
          </Box>

          <Box>
            <Text fontSize="sm" color="gray.500">
              Serial Number
            </Text>
            <Text fontWeight="medium">{printer.sn}</Text>
          </Box>

          <Box>
            <Text fontSize="sm" color="gray.500">
              IP Address
            </Text>
            <Text fontWeight="medium">{printer.ip}</Text>
          </Box>

          <Box>
            <Text fontSize="sm" color="gray.500">
              Location
            </Text>
            <Text fontWeight="medium">{printer.location}</Text>
          </Box>

          <HStack spacing={2} pt={2}>
            <IconButton
              aria-label="View printer"
              icon={<ViewIcon />}
              size="sm"
              colorScheme="blue"
              onClick={() => printer.id && onView(printer.id)}
            />
            <IconButton
              aria-label="Edit printer"
              icon={<EditIcon />}
              size="sm"
              colorScheme="orange"
              onClick={() => printer.id && onEdit(printer.id)}
            />
            <IconButton
              aria-label="Delete printer"
              icon={<DeleteIcon />}
              size="sm"
              colorScheme="red"
              onClick={() => printer.id && onDelete(printer.id)}
            />
          </HStack>
        </Stack>
      </CardBody>
    </Card>
  );
};

export default PrinterCard;
