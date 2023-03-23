package org.coders.youmarket.services.dtos.listing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.services.dtos.other.PhotoRequest;

import java.util.Set;

@Getter @Setter @ToString
public class ListingRequestResponse {
    private String listingReference;
    private String ownerReference;
    private Set<PhotoRequest> assets;
    private String price;
    private String description;
    private ListingTypeEnum listingType;
    private String updatedAt;
}