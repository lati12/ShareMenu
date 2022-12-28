import {ItemCategory} from "./item-category";

export class Item{
  id: number = 0;
  name:string = "";
  description:string = "";
  price:number = 0;
  unit:string = "";
  image:any = null;
  itemCategory: ItemCategory = new ItemCategory();
}
