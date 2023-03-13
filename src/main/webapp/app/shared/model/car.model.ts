export interface ICar {
  id?: number;
}

export class Car implements ICar {
  constructor(public id?: number) {}
}
