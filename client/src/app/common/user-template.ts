import {Template} from "./template";
import {Users} from "./users";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class UserTemplate {
  template : Template = new Template();
  user : Users = new Users();
}
