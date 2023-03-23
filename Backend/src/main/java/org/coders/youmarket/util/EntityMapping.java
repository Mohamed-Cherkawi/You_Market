package org.coders.youmarket.util;

import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.entities.Photo;
import org.coders.youmarket.entities.item.ItemListing;
import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.coders.youmarket.entities.vehicle.VehicleProperties;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;
import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.coders.youmarket.services.dtos.listing.vehicle.VehiclePropertiesRequest;
import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.coders.youmarket.services.dtos.other.PhotoRequest;
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
    public static VehicleProperties vehiclePropertiesRequestToVehicleProperties(VehiclePropertiesRequest vehiclePropertiesRequest){
        return modelMapper.map(vehiclePropertiesRequest, VehicleProperties.class);
    }

    public static ItemRequest itemListingToItemRequest(ItemListing itemListing){
        return modelMapper.map(itemListing, ItemRequest.class);
    }
    public static ItemListing itemRequestToItemListing(ItemRequest itemRequest){
        return modelMapper.map(itemRequest, ItemListing.class);
    }

    public static Photo photoRequestToPhoto(PhotoRequest photoRequest){
        return modelMapper.map(photoRequest,Photo.class);
    }
}