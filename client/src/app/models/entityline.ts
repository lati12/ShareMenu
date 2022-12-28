import {Entityheader} from "./entityheader";
import {Item} from "./item";

export class Entityline {
  id:number = 0;
  name:string = "";
  price: number = 0;
  quantity: number = 0;
  entityheader : Entityheader = new Entityheader();
  item : Item = new Item()
}
