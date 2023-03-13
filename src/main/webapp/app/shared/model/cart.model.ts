import { ICommand } from '@/shared/model/command.model';
import { IClient } from '@/shared/model/client.model';
import { IShop } from '@/shared/model/shop.model';

export interface ICart {
  id?: number;
  amount?: number;
  deadline?: number;
  command?: ICommand | null;
  client?: IClient | null;
  shop?: IShop | null;
}

export class Cart implements ICart {
  constructor(
    public id?: number,
    public amount?: number,
    public deadline?: number,
    public command?: ICommand | null,
    public client?: IClient | null,
    public shop?: IShop | null
  ) {}
}
