package org.coders.youmarket.services.dtos.listing.item;

import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;
import org.coders.youmarket.services.dtos.other.AddressRequest;

@Getter @Setter
public class ItemRequest extends ListingRequestResponse {
    private String title;
    private AddressRequest location;
    private String publicMeetup;
    private String doorPickup;
    private String doorDropOff;
    private ItemPropertiesRequest properties;
}