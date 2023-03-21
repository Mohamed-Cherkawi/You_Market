package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.springframework.http.ResponseEntity;

public interface VehicleListingServiceInterface {
    ResponseEntity<Object> createVehicleListing(VehicleRequest vehicleRequest);
    ResponseEntity<Object> updateVehicleListing(VehicleRequest vehicleRequest);
}