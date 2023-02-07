import {Roles} from "./Roles";

export  class Users {
  id:number = 0;
  name:string = "";
  lastname:string = "";
  password:string = "";
  email:string = "";
  companyName:string = "";
  roles: Roles[] = [];
}
