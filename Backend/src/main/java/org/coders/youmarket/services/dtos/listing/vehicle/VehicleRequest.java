package org.coders.youmarket.services.dtos.listing.vehicle;

import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;

@Getter @Setter
public class VehicleRequest extends ListingRequestResponse {
    private String purchaseDate;
    private VehiclePropertiesRequest properties;
}