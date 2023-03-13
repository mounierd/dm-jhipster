import { ICart } from '@/shared/model/cart.model';

export interface IShop {
  id?: number;
  addressS?: string;
  menu?: string | null;
  carts?: ICart[] | null;
}

export class Shop implements IShop {
  constructor(public id?: number, public addressS?: string, public menu?: string | null, public carts?: ICart[] | null) {}
}
