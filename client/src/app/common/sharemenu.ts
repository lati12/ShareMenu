import {SocialNetworkProvider} from "./socialnetworkprovider";
import {EntityHeader} from "./entityheader";

//Модел клас, който служи за транспортиране на данни между angular и spring boot

export class  Sharemenu{
  id:number = 0;
  socialNetworkProvider: SocialNetworkProvider = new SocialNetworkProvider();

  entityHeader : EntityHeader = new EntityHeader();
}
