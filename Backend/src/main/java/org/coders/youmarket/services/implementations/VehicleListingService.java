package org.coders.youmarket.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.Photo;
import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.repositories.VehicleListingRepository;
import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.coders.youmarket.services.interfaces.VehicleListingServiceInterface;
import org.coders.youmarket.util.DateTimeParser;
import org.coders.youmarket.util.EntityMapping;
import org.coders.youmarket.util.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class VehicleListingService implements VehicleListingServiceInterface {
    private final VehicleListingRepository vehicleListingRepository;

    @Override @Transactional
    public ResponseEntity<Object> createVehicleListing(VehicleRequest vehicleRequest) {
        VehicleListing vehicleListing = EntityMapping.vehicleRequestToVehicleListing(vehicleRequest);

        vehicleListing.setAssets(
                vehicleRequest.getAssets().stream()
                        .map(imageUrl -> Photo.builder()
                                .imageUrl(imageUrl)
                                .build())
                        .collect(Collectors.toSet())
        );
        vehicleListing.setListingReference(UUID.randomUUID().toString());
        vehicleListing.setPurchaseDate(DateTimeParser.getDateFromFormatPattern(
                vehicleRequest.getPurchaseDate()
        ));
        vehicleListing.setListingType(ListingTypeEnum.VEHICLE);

        try {
            return ResponseHandler.generateResponse("The Vehicle Listing Has Been Created Successfully", HttpStatus.CREATED,vehicleListingRepository.save(vehicleListing));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse("The Vehicle Listing Hasn't Created , Something went wrong please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override @Transactional
    public ResponseEntity<Object> updateVehicleListing(VehicleRequest vehicleRequest) {
        return null;
    }
}