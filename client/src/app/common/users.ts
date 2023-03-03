import {Roles} from "./Roles";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export  class Users {
  id:number = 0;
  name:string = "";
  lastname:string = "";
  password:string = "";
  email:string = "";
  companyName:string = "";
  emailConfirmed:boolean = false;
  enabled:boolean = false;

  roles: Roles[] = [];
}
