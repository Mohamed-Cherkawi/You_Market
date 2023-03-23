package org.coders.youmarket.services.interfaces;

import org.springframework.http.ResponseEntity;

public interface ListingServiceInterface {
    ResponseEntity<Object> getAllListings();
    ResponseEntity<Object> getAllListingsByOwnerReference(String ownerReference);
    ResponseEntity<Object> getListingByReference(String reference);
    ResponseEntity<Object> deleteListing(String reference);
}