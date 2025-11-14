import axios from 'axios';
import { Printer, Counter } from '../types';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Printer API
export const printerApi = {
  getAll: () => api.get<Printer[]>('/printers'),
  getById: (id: number) => api.get<Printer>(`/printers/${id}`),
  create: (printer: Printer) => api.post<Printer>('/printers', printer),
  update: (id: number, printer: Printer) => api.put<Printer>(`/printers/${id}`, printer),
  delete: (id: number) => api.delete(`/printers/${id}`),
};

// Counter API
export const counterApi = {
  getAll: () => api.get<Counter[]>('/counters'),
  getByIp: (ip: string) => api.get<Counter[]>(`/counters/ip/${ip}`),
  create: (counter: Counter) => api.post<Counter>('/counters', counter),
};

export default api;
