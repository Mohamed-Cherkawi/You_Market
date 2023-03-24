package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.dtos.listing.home.HomeRentalRequest;
import org.coders.youmarket.services.interfaces.HomeRentalListingServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home-rental")
@RequiredArgsConstructor
public class HomeRentalListingController {
    private final HomeRentalListingServiceInterface homeRentalListingService;

    @PostMapping("/creating")
    public ResponseEntity<Object> createHomeRentalListingApi(@RequestBody HomeRentalRequest homeRentalRequest){
        return homeRentalListingService.createHomeRentalListing(homeRentalRequest);
    }

    @PutMapping("/updating")
    public ResponseEntity<Object> updateHomeRentalListingApi(@RequestBody HomeRentalRequest homeRentalRequest){
        return homeRentalListingService.updateHomeRentalListing(homeRentalRequest);
    }
}