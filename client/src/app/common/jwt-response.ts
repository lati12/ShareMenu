import {Roles} from "./Roles";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class JwtResponse {
  public jwt: string = "";
  public type: string = "Bearer";
  public id: number = 0;
  public email: string = "";
  public roles: string[] = [];
}
