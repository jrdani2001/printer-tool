import { useState, useEffect } from 'react';
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Button,
  FormControl,
  FormLabel,
  Input,
  VStack,
  useToast,
} from '@chakra-ui/react';
import { printerApi } from '../services/api';
import { Printer } from '../types';

interface PrinterFormProps {
  isOpen: boolean;
  onClose: () => void;
  printer?: Printer;
  onSuccess: () => void;
}

const PrinterForm = ({ isOpen, onClose, printer, onSuccess }: PrinterFormProps) => {
  const [formData, setFormData] = useState<Printer>({
    manufacturer: '',
    type: '',
    sn: '',
    ip: '',
    location: '',
  });
  const [isLoading, setIsLoading] = useState(false);
  const toast = useToast();

  useEffect(() => {
    if (printer) {
      setFormData(printer);
    } else {
      setFormData({
        manufacturer: '',
        type: '',
        sn: '',
        ip: '',
        location: '',
      });
    }
  }, [printer]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);

    try {
      if (printer?.id) {
        await printerApi.update(printer.id, formData);
        toast({
          title: 'Printer updated',
          description: 'The printer has been successfully updated.',
          status: 'success',
          duration: 3000,
          isClosable: true,
        });
      } else {
        await printerApi.create(formData);
        toast({
          title: 'Printer created',
          description: 'The printer has been successfully created.',
          status: 'success',
          duration: 3000,
          isClosable: true,
        });
      }
      onSuccess();
      onClose();
    } catch (error) {
      toast({
        title: 'Error',
        description: 'Failed to save printer. Please try again.',
        status: 'error',
        duration: 3000,
        isClosable: true,
      });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose} size="xl">
      <ModalOverlay />
      <ModalContent>
        <form onSubmit={handleSubmit}>
          <ModalHeader>{printer ? 'Edit Printer' : 'Add New Printer'}</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <VStack spacing={4}>
              <FormControl isRequired>
                <FormLabel>Manufacturer</FormLabel>
                <Input
                  value={formData.manufacturer}
                  onChange={(e) =>
                    setFormData({ ...formData, manufacturer: e.target.value })
                  }
                  placeholder="e.g., HP, Canon, Epson"
                />
              </FormControl>

              <FormControl isRequired>
                <FormLabel>Model/Type</FormLabel>
                <Input
                  value={formData.type}
                  onChange={(e) => setFormData({ ...formData, type: e.target.value })}
                  placeholder="e.g., LaserJet Pro M404dn"
                />
              </FormControl>

              <FormControl isRequired>
                <FormLabel>Serial Number</FormLabel>
                <Input
                  value={formData.sn}
                  onChange={(e) => setFormData({ ...formData, sn: e.target.value })}
                  placeholder="e.g., ABC123456789"
                />
              </FormControl>

              <FormControl isRequired>
                <FormLabel>IP Address</FormLabel>
                <Input
                  value={formData.ip}
                  onChange={(e) => setFormData({ ...formData, ip: e.target.value })}
                  placeholder="e.g., 192.168.1.100"
                />
              </FormControl>

              <FormControl isRequired>
                <FormLabel>Location</FormLabel>
                <Input
                  value={formData.location}
                  onChange={(e) =>
                    setFormData({ ...formData, location: e.target.value })
                  }
                  placeholder="e.g., Office 2nd Floor"
                />
              </FormControl>
            </VStack>
          </ModalBody>

          <ModalFooter>
            <Button variant="ghost" mr={3} onClick={onClose}>
              Cancel
            </Button>
            <Button colorScheme="blue" type="submit" isLoading={isLoading}>
              {printer ? 'Update' : 'Create'}
            </Button>
          </ModalFooter>
        </form>
      </ModalContent>
    </Modal>
  );
};

export default PrinterForm;
