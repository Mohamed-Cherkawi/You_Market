package org.coders.youmarket.util;

import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;
import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.coders.youmarket.services.dtos.user.ProfilePreviewResponse;
import org.modelmapper.ModelMapper;

public class EntityMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    private EntityMapping() {
    }

    public static ProfilePreviewResponse userToProfilePreviewResponse(AppUser user) {
        return modelMapper.map(user, ProfilePreviewResponse.class);
    }
    public static ListingRequestResponse userToListingOverviewResponse(Listing listing) {
        return modelMapper.map(listing, ListingRequestResponse.class);
    }

    public static VehicleRequest vehicleListingToVehicleRequest(Listing vehicleListing){
        return modelMapper.map(vehicleListing, VehicleRequest.class);
    }
    public static VehicleListing vehicleRequestToVehicleListing(VehicleRequest vehicleRequest){
        return modelMapper.map(vehicleRequest, VehicleListing.class);
    }
}