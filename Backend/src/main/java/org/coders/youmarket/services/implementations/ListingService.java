package org.coders.youmarket.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.repositories.ListingRepository;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;
import org.coders.youmarket.services.interfaces.ListingServiceInterface;
import org.coders.youmarket.util.DateTimeParser;
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
                    HttpStatus.NOT_FOUND
            );
        }

        return getSuccessfulMessage(listings);
    }

    @Override
    public ResponseEntity<Object> getAllListingsByListingType(ListingTypeEnum listingType) {
        List<Listing> listings = listingRepository.findAllByListingType(listingType);

        if(listings.isEmpty()){
            return ResponseHandler.generateResponse(
                    "There is no listings with the given listings type : " + listingType.name(),
                    HttpStatus.NOT_FOUND
            );
        }

        return getSuccessfulMessage(listings);
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

        return getSuccessfulMessage(listings);
    }

    @Override
    public ResponseEntity<Object> getListingByReference(String reference) {
        Optional<Listing> listing = listingRepository.findByListingReference(reference);

        return listing.map(
                value -> ResponseHandler.generateResponse(
                        "The Listing Has Ben Fetched Successfully",
                        HttpStatus.OK,
                        mapListingToItsTypeResponse(value,value.getListingType()),
                        HeaderKeyValueResponse.builder().
                                key("Listing-Type").
                                value(value.getListingType().name())
                                .build())
                )
                .orElseGet(() -> ResponseHandler.generateResponse(
                "There is no listing with the following reference : " + reference,
                HttpStatus.BAD_REQUEST));

    }

    @Override @Transactional
    public ResponseEntity<Object> deleteListing(String reference) {
        Integer response = listingRepository.deleteByListingReference(reference);

        return ( response == 1 )
                ? ResponseHandler.generateResponse(
                "The Listing with the " + reference + " reference has been deleted successfully",
                HttpStatus.GONE)
                : ResponseHandler.generateResponse(
                "The Listing with the " + reference + " reference has not been deleted successfully , please try again",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Set<ListingRequestResponse> mapListingsToOverviewResponse(List<Listing> listings){
        return listings.stream()
                .map(listing -> {
                    ListingRequestResponse listingRequestResponse = EntityMapping.listingToListingRequestResponse(listing);

                    if(listing.getUpdatedAt() != null){
                        listingRequestResponse.setUpdatedAt(
                                DateTimeParser.getStringDateOfLocalDateTimeStandardPattern(listing.getUpdatedAt())
                        );
                    }

                    return listingRequestResponse;
                })
                .collect(Collectors.toSet());
    }
    private ListingRequestResponse mapListingToItsTypeResponse(Listing listing , ListingTypeEnum listingType){
        ListingRequestResponse listingRequestResponse ;

        if(listingType.equals(ListingTypeEnum.VEHICLE)){
            listingRequestResponse = EntityMapping.listingToVehicleRequest(listing);
        } else if (listingType.equals(ListingTypeEnum.ITEM)) {
            listingRequestResponse = EntityMapping.listingToItemRequest(listing);
        }else {
            listingRequestResponse = EntityMapping.listingToHomeRentalRequest(listing);
        }

        if(listing.getUpdatedAt() != null){
            listingRequestResponse.setUpdatedAt(
                    DateTimeParser.getStringDateOfLocalDateTimeStandardPattern(listing.getUpdatedAt())
            );
        }

        return listingRequestResponse;
    }

    private ResponseEntity<Object> getSuccessfulMessage(List<Listing> listings){
        return ResponseHandler.generateResponse(
                "Data Fetched Successfully",
                HttpStatus.OK,
                mapListingsToOverviewResponse(listings)
        );
    }
}