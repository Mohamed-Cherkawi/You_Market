package org.coders.youmarket.util;

import org.coders.youmarket.entities.Address;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.entities.Photo;
import org.coders.youmarket.entities.home.HomeRentalListing;
import org.coders.youmarket.entities.home.HomeRentalProperties;
import org.coders.youmarket.entities.item.ItemListing;
import org.coders.youmarket.entities.item.ItemProperties;
import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.coders.youmarket.entities.vehicle.VehicleProperties;
import org.coders.youmarket.services.dtos.listing.ListingRequestResponse;
import org.coders.youmarket.services.dtos.listing.home.HomeRentalPropertiesRequest;
import org.coders.youmarket.services.dtos.listing.home.HomeRentalRequest;
import org.coders.youmarket.services.dtos.listing.item.ItemPropertiesRequest;
import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.coders.youmarket.services.dtos.listing.vehicle.VehiclePropertiesRequest;
import org.coders.youmarket.services.dtos.listing.vehicle.VehicleRequest;
import org.coders.youmarket.services.dtos.other.AddressRequest;
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
    public static ListingRequestResponse listingToListingRequestResponse(Listing listing) {
        return modelMapper.map(listing, ListingRequestResponse.class);
    }

    public static Address addressRequestToAddress(AddressRequest addressRequest){
        return modelMapper.map(addressRequest , Address.class);
    }

    public static VehicleRequest listingToVehicleRequest(Listing vehicleListing){
        return modelMapper.map(vehicleListing, VehicleRequest.class);
    }
    public static VehicleListing vehicleRequestToVehicleListing(VehicleRequest vehicleRequest){
        return modelMapper.map(vehicleRequest, VehicleListing.class);
    }
    public static VehicleProperties vehiclePropertiesRequestToVehicleProperties(VehiclePropertiesRequest vehiclePropertiesRequest){
        return modelMapper.map(vehiclePropertiesRequest, VehicleProperties.class);
    }

    public static ItemRequest listingToItemRequest(Listing itemListing){
        return modelMapper.map(itemListing, ItemRequest.class);
    }
    public static ItemRequest itemListingToItemRequest(ItemListing itemListing){
        return modelMapper.map(itemListing, ItemRequest.class);
    }
    public static ItemListing itemRequestToItemListing(ItemRequest itemRequest){
        return modelMapper.map(itemRequest, ItemListing.class);
    }
    public static ItemProperties itemPropertiesRequestToItemProperties(ItemPropertiesRequest itemPropertiesRequest){
        return modelMapper.map(itemPropertiesRequest, ItemProperties.class);
    }

    public static HomeRentalRequest listingToHomeRentalRequest(Listing homeListing){
        return modelMapper.map(homeListing, HomeRentalRequest.class);
    }
//    public static HomeRentalRequest homeRentalListingToHomeRentalRequest(HomeRentalListing homeRentalListing){
//        return modelMapper.map(homeRentalListing, HomeRentalRequest.class);
//    }
    public static HomeRentalListing homeRentalRequestToHomeRentalListing(HomeRentalRequest homeRentalRequest){
        return modelMapper.map(homeRentalRequest, HomeRentalListing.class);
    }
    public static HomeRentalProperties homeRentalPropertiesRequestToHomeRentalProperties(
            HomeRentalPropertiesRequest homeRentalPropertiesRequest){
        return modelMapper.map(homeRentalPropertiesRequest, HomeRentalProperties.class);
    }

    public static Photo photoRequestToPhoto(PhotoRequest photoRequest){
        return modelMapper.map(photoRequest,Photo.class);
    }
}