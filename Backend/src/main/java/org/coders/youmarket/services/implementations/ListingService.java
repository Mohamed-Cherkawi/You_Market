package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.entities.Photo;
import org.coders.youmarket.repositories.ListingRepository;
import org.coders.youmarket.services.dtos.listing.ListingOverviewResponse;
import org.coders.youmarket.services.interfaces.ListingServiceInterface;
import org.coders.youmarket.util.EntityMapping;
import org.coders.youmarket.util.HeaderKeyValueResponse;
import org.coders.youmarket.util.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ListingService implements ListingServiceInterface {
    private final ListingRepository listingRepository;


    @Override
    public ResponseEntity<Object> getAllListings() {
        List<Listing> listings = listingRepository.findAll();

        if(listings.isEmpty()){
            return ResponseHandler.generateResponse(
                    "There Is No Listings To Show",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }

        return ResponseHandler.generateResponse(
                "Data Fetched Successfully",
                HttpStatus.OK,
                mapListingsToOverviewResponse(listings)
        );
    }

    @Override
    public ResponseEntity<Object> getAllListingsByOwnerReference(String ownerReference) {
        List<Listing> listings = listingRepository.findAllByOwnerReference(ownerReference);

        if(listings.isEmpty()){
            return ResponseHandler.generateResponse(
                    "There Is No Listings To Show with the given owner reference : " +ownerReference,
                    HttpStatus.NOT_FOUND,
                    null
            );
        }

        return ResponseHandler.generateResponse(
                "Data Fetched Successfully",
                HttpStatus.OK,
                mapListingsToOverviewResponse(listings)
        );
    }

    @Override
    public ResponseEntity<Object> getListingByReference(String reference) {
        Optional<Listing> listing = listingRepository.findByListingReference(reference);

        return listing.map(
                value -> ResponseHandler.generateResponse(
                        "The Listing Has Ben Fetched Successfully",
                        HttpStatus.OK,
                        value,
                        HeaderKeyValueResponse.builder().
                                key("Listing-Type").
                                value(value.getListingType().name())
                                .build())
                )
                .orElseGet(() -> ResponseHandler.generateResponse(
                "There is no listing with the following reference : " + reference,
                HttpStatus.BAD_REQUEST));

    }

    @Override
    public ResponseEntity<Object> createListing() {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateListing() {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteListing() {
        return null;
    }

    private Set<ListingOverviewResponse> mapListingsToOverviewResponse(List<Listing> listings){
        return listings.stream()
                .map(listing -> {
                    Set<String> assets = listing.getAssets().stream()
                            .map(Photo::getImageUrl)
                            .collect(Collectors.toSet());

                    ListingOverviewResponse listingOverview = EntityMapping.userToListingOverviewResponse(listing);
                    listingOverview.setAssets(assets);

                    return listingOverview;
                })
                .collect(Collectors.toSet());
    }
}