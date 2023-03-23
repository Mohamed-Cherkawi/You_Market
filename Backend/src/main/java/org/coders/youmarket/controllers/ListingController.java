package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.interfaces.ListingServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listing") @RequiredArgsConstructor
public class ListingController {
    private final ListingServiceInterface listingService;

    @GetMapping("/fetching/all")
    public ResponseEntity<Object> getAllListingsApi(){
        return listingService.getAllListings();
    }

    @GetMapping("/fetching/single/{listingReference}")
    public ResponseEntity<Object> getListingByReferenceApi(@PathVariable("listingReference") String listingReference){
        return listingService.getListingByReference(listingReference);
    }

    @GetMapping("/fetching-by-owner/single/{ownerReference}")
    public ResponseEntity<Object> getAllListingsByOwnerReferenceApi(@PathVariable("ownerReference") String ownerReference){
        return listingService.getAllListingsByOwnerReference(ownerReference);
    }

    @DeleteMapping("/deleting")
    public ResponseEntity<Object> deleteListingByReferenceApi(@RequestHeader("Listing-Reference") String reference){
        return listingService.deleteListing(reference);
    }
}