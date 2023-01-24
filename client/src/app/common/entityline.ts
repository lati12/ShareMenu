import {Item} from "./item";
import {EntityHeader} from "./entityheader";

export class Entityline {
  id:number = 0;
  price: number = 0;
  quantity: number = 0;
  entityHeader : EntityHeader = new EntityHeader();
  item : Item = new Item()
}
