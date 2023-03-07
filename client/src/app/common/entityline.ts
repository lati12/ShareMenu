import {Item} from "./item";
import {EntityHeader} from "./entityheader";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class Entityline {
  id:number = 0;
  price: number = 0;
  quantity: number = 0;
  entityHeader : EntityHeader = new EntityHeader();
  item : Item = new Item();
}
