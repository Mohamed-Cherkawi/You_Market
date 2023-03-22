package org.coders.youmarket.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.repositories.VehicleListingRepository;
import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.coders.youmarket.services.interfaces.VehicleListingServiceInterface;
import org.coders.youmarket.util.AssetsMapper;
import org.coders.youmarket.util.DateTimeParser;
import org.coders.youmarket.util.EntityMapping;
import org.coders.youmarket.util.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class VehicleListingService implements VehicleListingServiceInterface {
    private final VehicleListingRepository vehicleListingRepository;

    @Override @Transactional
    public ResponseEntity<Object> createVehicleListing(VehicleRequest vehicleRequest) {
        VehicleListing vehicleListing = EntityMapping.vehicleRequestToVehicleListing(vehicleRequest);

        vehicleListing.setAssets(
                AssetsMapper.mapSetOfStringsToSetOfPhotos(vehicleRequest.getAssets())
        );
        vehicleListing.setListingReference(UUID.randomUUID().toString());
        vehicleListing.setPurchaseDate(DateTimeParser.getDateFromFormatPattern(
                vehicleRequest.getPurchaseDate()
        ));
        vehicleListing.setListingType(ListingTypeEnum.VEHICLE);

        try {
            return ResponseHandler.generateResponse(
                    "The Vehicle Listing Has Been Created Successfully",
                    HttpStatus.CREATED,
                    mapVehicleListingToVehicleRequestResponse(vehicleListingRepository.save(vehicleListing)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse(
                    "The Vehicle Listing Hasn't Created , Something went wrong please try again",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override @Transactional
    public ResponseEntity<Object> updateVehicleListing(VehicleRequest vehicleRequest) {
        VehicleListing vehicleListing = vehicleListingRepository.findByListingReference(vehicleRequest.getListingReference()).orElse(null);

        if(vehicleListing == null){
            return ResponseHandler.generateResponse(
                    "There is no listing with the given reference : " + vehicleRequest.getListingReference() + " to be updated !",
                    HttpStatus.BAD_REQUEST);
        }

        vehicleListing.setDescription(vehicleRequest.getDescription());
        vehicleListing.setPrice(Float.parseFloat(vehicleRequest.getPrice()));
        vehicleListing.setAssets(
                AssetsMapper.mapSetOfStringsToSetOfPhotos(vehicleRequest.getAssets())
        );
        vehicleListing.setPurchaseDate(
                DateTimeParser.getDateFromFormatPattern(vehicleRequest.getPurchaseDate())
        );
        vehicleListing.setProperties(
                EntityMapping.vehiclePropertiesRequestToVehicleProperties(vehicleRequest.getProperties())
        );
        vehicleListing.setUpdatedAt(LocalDateTime.now());

        try {
            return ResponseHandler.generateResponse(
                    "The Vehicle Listing Has Been Updated Successfully",
                    HttpStatus.OK,
                    mapVehicleListingToVehicleRequestResponse(vehicleListingRepository.save(vehicleListing)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse(
                    "The Vehicle Listing Hasn't Been Updated , Something went wrong please try again",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    private VehicleRequest mapVehicleListingToVehicleRequestResponse(VehicleListing vehicleListing){
        VehicleRequest vehicleResponse = EntityMapping.vehicleListingToVehicleRequest(vehicleListing);

        vehicleResponse.setAssets(
                AssetsMapper.mapSetOfPhotosToSetOfStrings(vehicleListing.getAssets())
        );
        vehicleResponse.setUpdatedAt(
                DateTimeParser.getStringDateOfLocalDateTimeStandardPattern(vehicleListing.getUpdatedAt())
        );
        return vehicleResponse;
    }
}