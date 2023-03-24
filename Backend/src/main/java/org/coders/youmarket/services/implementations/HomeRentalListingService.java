package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.home.HomeRentalListing;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.repositories.HomeRentalRepository;
import org.coders.youmarket.services.dtos.listing.home.HomeRentalRequest;
import org.coders.youmarket.services.interfaces.HomeRentalListingServiceInterface;
import org.coders.youmarket.util.DateTimeParser;
import org.coders.youmarket.util.EntityMapping;
import org.coders.youmarket.util.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeRentalListingService implements HomeRentalListingServiceInterface {
    private final HomeRentalRepository homeRentalRepository;

    @Override
    public ResponseEntity<Object> createHomeRentalListing(HomeRentalRequest homeRentalRequest) {
        HomeRentalListing homeRentalListing = EntityMapping.homeRentalRequestToHomeRentalListing(homeRentalRequest);

        homeRentalListing.setListingReference(UUID.randomUUID().toString());
        homeRentalListing.setListingType(ListingTypeEnum.HOME);
        homeRentalListing.setAvailableAt(
                DateTimeParser.getLocalDateTimeFromFormatPattern(homeRentalRequest.getAvailableAt())
        );

        try {
            return ResponseHandler.generateResponse(
                    "The Home Rental Listing Has Been Created Successfully",
                    HttpStatus.CREATED,
                    mapHomeRentalListingToHomeRentalRequest(homeRentalRepository.save(homeRentalListing),false)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse(
                    "The Home Rental Listing Hasn't Created , Something went wrong please try again , and make sure all the required attributes are filled up",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateHomeRentalListing(HomeRentalRequest homeRentalRequest) {
        HomeRentalListing homeRentalListing = homeRentalRepository.findByListingReference(homeRentalRequest.getListingReference())
                .orElse(null);

        if(homeRentalListing == null){
            return ResponseHandler.generateResponse(
                    "There is no home rental listing with the given reference : " + homeRentalRequest.getListingReference() + " to be updated !",
                    HttpStatus.BAD_REQUEST);
        }

        homeRentalListing.setDescription(homeRentalRequest.getDescription());
        homeRentalListing.setPrice(homeRentalRequest.getPrice());
        homeRentalListing.setAssets(
                homeRentalRequest.getAssets().stream()
                        .map(EntityMapping::photoRequestToPhoto)
                        .collect(Collectors.toSet())
        );
        homeRentalListing.setAvailableAt(
                DateTimeParser.getLocalDateTimeFromFormatPattern(homeRentalRequest.getAvailableAt())
        );
        homeRentalListing.setAddress(
                EntityMapping.addressRequestToAddress(homeRentalRequest.getAddress())
        );
        homeRentalListing.setProperties(
                EntityMapping.homeRentalPropertiesRequestToHomeRentalProperties(homeRentalRequest.getProperties())
        );
        homeRentalListing.setUpdatedAt(LocalDateTime.now());

        try {
            return ResponseHandler.generateResponse(
                    "The Home Rental Listing Has Been Updated Successfully",
                    HttpStatus.OK,
                    mapHomeRentalListingToHomeRentalRequest(homeRentalRepository.save(homeRentalListing),true));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse(
                    "The Home Rental Listing Hasn't Been Updated , Something went wrong please try again",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private HomeRentalRequest mapHomeRentalListingToHomeRentalRequest(HomeRentalListing homeRentalListing , boolean setUpdatedAt){
        HomeRentalRequest homeRentalRequest = EntityMapping.listingToHomeRentalRequest(homeRentalListing);

        if(setUpdatedAt) {
            homeRentalRequest.setUpdatedAt(
                    DateTimeParser.getStringDateOfLocalDateTimeStandardPattern(homeRentalListing.getUpdatedAt())
            );
        }
        homeRentalRequest.setAvailableAt(
                DateTimeParser.getStringDateOfLocalDateTimeStandardPattern(homeRentalListing.getAvailableAt())
        );

        return homeRentalRequest;
    }
}