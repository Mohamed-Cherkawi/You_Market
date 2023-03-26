import {AddressRequest} from "../other/address-request.model";

export interface RegisterRequest {
  username: string;
  password: string;
  name: string;
  phone: string;
  address: AddressRequest;
}
