import {Template} from "./template";
import {Users} from "./users";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class EntityHeader {
  id:number = 0;
  name:string = "";
  address:string = "";
  phone:string = "";
  email:string = "";
  city:string = "";
  country:string = "";
  template: Template = new Template();
  users:Users = new Users();
}
