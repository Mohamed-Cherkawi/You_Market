package org.coders.youmarket.services.dtos.listing.home;

import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;
import org.coders.youmarket.services.dtos.other.AddressRequest;

@Getter @Setter
public class HomeRentalRequest extends ListingRequestResponse {
    private AddressRequest address;
    private String availableAt;
    private HomeRentalPropertiesRequest properties;
}