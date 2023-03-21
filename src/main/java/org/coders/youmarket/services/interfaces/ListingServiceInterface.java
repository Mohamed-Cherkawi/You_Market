package org.coders.youmarket.services.interfaces;

import org.springframework.http.ResponseEntity;

public interface ListingServiceInterface {
    ResponseEntity<Object> getAllListing();
    ResponseEntity<Object> getListingByReference(String reference);
    ResponseEntity<Object> createListing();
    ResponseEntity<Object> updateListing();
    ResponseEntity<Object> deleteListing();
}