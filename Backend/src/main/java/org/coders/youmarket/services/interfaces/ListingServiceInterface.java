package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.springframework.http.ResponseEntity;

public interface ListingServiceInterface {
    ResponseEntity<Object> getAllListings();
    ResponseEntity<Object> getAllListingsByListingType(ListingTypeEnum listingType);
    ResponseEntity<Object> getAllListingsByOwnerReference(String ownerReference);
    ResponseEntity<Object> getListingByReference(String reference);
    ResponseEntity<Object> deleteListing(String reference);
}