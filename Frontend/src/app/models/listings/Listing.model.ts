import {Photo} from "../other/photo.model";
import {ListingType} from "../../enums/listing-type.enum";
import {FileHandle} from "../other/file-handle.model";

export interface Listing {
  listingReference?: string | null;
  ownerReference: string | null;
  assets?: Photo[] | null;
  price: number;
  description: string;
  listingType: ListingType;
  updatedAt?: string | null;
  fileHandling: FileHandle[];
}
