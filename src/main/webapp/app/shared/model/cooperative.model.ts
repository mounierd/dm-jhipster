import { IDriver } from '@/shared/model/driver.model';

export interface ICooperative {
  id?: string;
  name?: string;
  drivers?: IDriver[] | null;
}

export class Cooperative implements ICooperative {
  constructor(public id?: string, public name?: string, public drivers?: IDriver[] | null) {}
}
