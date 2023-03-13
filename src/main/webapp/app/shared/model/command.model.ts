import { IClient } from '@/shared/model/client.model';
import { ICart } from '@/shared/model/cart.model';
import { IDriver } from '@/shared/model/driver.model';

export interface ICommand {
  id?: number;
  addressClient?: string;
  dateClient?: string;
  client?: IClient | null;
  cart?: ICart | null;
  driver?: IDriver | null;
}

export class Command implements ICommand {
  constructor(
    public id?: number,
    public addressClient?: string,
    public dateClient?: string,
    public client?: IClient | null,
    public cart?: ICart | null,
    public driver?: IDriver | null
  ) {}
}
