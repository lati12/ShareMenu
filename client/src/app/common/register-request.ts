import {Roles} from "./Roles";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class RegisterRequest {
  public email: string = "";
  public password: string = "";
  public name: string = "";
  public lastName: string = "";
  public companyName: string = "";
  public role: String[] = [];
}
