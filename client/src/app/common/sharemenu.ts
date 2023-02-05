import {SocialNetworkProvider} from "./socialnetworkprovider";
import {EntityHeader} from "./entityheader";

export class  Sharemenu{
  id:number = 0;
  socialNetworkProvider: SocialNetworkProvider = new SocialNetworkProvider();

  entityHeader : EntityHeader = new EntityHeader();
}
