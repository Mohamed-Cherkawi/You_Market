import {Photo} from "../other/photo.model";
import {ListingType} from "../../enums/listing-type.enum";

export interface Listing {
  listingReference?: string | null;
  ownerReference: string;
  assets?: Photo[] | null;
  price: number;
  description: string;
  listingType: ListingType;
  updatedAt?: string | null;
}
