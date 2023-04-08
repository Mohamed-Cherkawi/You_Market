import {Listing} from "../Listing.model";
import {AddressRequest} from "../../other/address-request.model";
import {ItemProperties} from "./item-properties.model";
import {FileHandle} from "../../other/file-handle.model";

export interface ItemListing extends Listing{
  title: string;
  location: AddressRequest;
  publicMeetup: boolean;
  doorPickup: boolean;
  doorDropOff: boolean;
  properties: ItemProperties;
  fileHandling: FileHandle[];
}
