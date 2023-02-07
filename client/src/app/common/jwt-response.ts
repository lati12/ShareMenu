import {Roles} from "./Roles";

export class JwtResponse {
  public jwt: string = "";
  public type: string = "Bearer";
  public id: number = 0;
  public email: string = "";
  public roles: string[] = [];
}
