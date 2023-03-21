package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.coders.youmarket.services.interfaces.VehicleListingServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listing/vehicle")
@RequiredArgsConstructor
public class VehicleListingController {
    private final VehicleListingServiceInterface vehicleListingService;

    @PostMapping("/creating")
    public ResponseEntity<Object> createVehicleListingApi(@RequestBody VehicleRequest vehicleRequest){
        return vehicleListingService.createVehicleListing(vehicleRequest);
    }

    @PutMapping("/updating")
    public ResponseEntity<Object> updateVehicleListingApi(@RequestBody VehicleRequest vehicleRequest){
        return vehicleListingService.updateVehicleListing(vehicleRequest);
    }
}
