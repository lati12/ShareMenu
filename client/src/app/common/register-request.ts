import {Roles} from "./Roles";

export class RegisterRequest {
  public email: string = "";
  public password: string = "";
  public name: string = "";
  public lastName: string = "";
  public companyName: string = "";
  public role: String[] = [];
}
