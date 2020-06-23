export interface IProduct {
  id?: string;
  sku?: string;
  name?: string;
  observation?: string;
  price?: number;
}

export class Product implements IProduct {
  constructor(public id?: string, public sku?: string, public name?: string, public observation?: string, public price?: number) {}
}
