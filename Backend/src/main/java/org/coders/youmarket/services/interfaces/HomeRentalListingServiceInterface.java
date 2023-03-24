package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.services.dtos.listing.home.HomeRentalRequest;
import org.springframework.http.ResponseEntity;

public interface HomeRentalListingServiceInterface {
    ResponseEntity<Object> createHomeRentalListing(HomeRentalRequest homeRentalRequest);
    ResponseEntity<Object> updateHomeRentalListing(HomeRentalRequest homeRentalRequest);
}