package org.coders.youmarket.services.dtos.listing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.coders.youmarket.enums.listing.ListingTypeEnum;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter @ToString
public class ListingRequestResponse {
    private String listingReference;
    private String ownerReference;
    private Set<String> assets;
    private String price;
    private String description;
    private ListingTypeEnum listingType;
    private LocalDateTime updatedAt;
}