export interface Printer {
  id?: number;
  manufacturer: string;
  type: string;
  sn: string;
  ip: string;
  location: string;
}

export interface Counter {
  id?: number;
  type: string;
  ip: string;
  location: string;
  sn: string;
  counter: number;
}
