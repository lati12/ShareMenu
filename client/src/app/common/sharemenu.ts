import {SocialNetworkConnectivity} from "./socialNetworkConnectivity";
import {EntityHeader} from "./entityheader";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class  Sharemenu{
  id:number = 0;
  socialNetworkConnectivity: SocialNetworkConnectivity = new SocialNetworkConnectivity();

  entityHeader : EntityHeader = new EntityHeader();
}
