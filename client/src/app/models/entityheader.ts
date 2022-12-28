import {Template} from "./template";
import {Users} from "./users";

export class Entityheader {
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
