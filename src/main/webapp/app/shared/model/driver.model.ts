import { ICommand } from '@/shared/model/command.model';
import { ICooperative } from '@/shared/model/cooperative.model';

export interface IDriver {
  id?: number;
  firstnameDriver?: string;
  lastnameDriver?: string;
  phoneCountryCodeDriver?: number | null;
  phoneDriver?: number | null;
  commands?: ICommand[] | null;
  cooperative?: ICooperative | null;
}

export class Driver implements IDriver {
  constructor(
    public id?: number,
    public firstnameDriver?: string,
    public lastnameDriver?: string,
    public phoneCountryCodeDriver?: number | null,
    public phoneDriver?: number | null,
    public commands?: ICommand[] | null,
    public cooperative?: ICooperative | null
  ) {}
}
