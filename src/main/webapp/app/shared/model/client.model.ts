import { ICart } from '@/shared/model/cart.model';
import { ICommand } from '@/shared/model/command.model';

export interface IClient {
  id?: number;
  idClient?: string;
  firstnameClient?: string;
  lastnameClient?: string;
  emailClient?: string;
  phoneCountryCodeClient?: number | null;
  phoneClient?: number | null;
  addressC?: string;
  carts?: ICart[] | null;
  commands?: ICommand[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public idClient?: string,
    public firstnameClient?: string,
    public lastnameClient?: string,
    public emailClient?: string,
    public phoneCountryCodeClient?: number | null,
    public phoneClient?: number | null,
    public addressC?: string,
    public carts?: ICart[] | null,
    public commands?: ICommand[] | null
  ) {}
}
