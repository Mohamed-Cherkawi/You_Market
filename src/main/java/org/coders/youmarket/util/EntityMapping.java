package org.coders.youmarket.util;

import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.services.dtos.listing.ListingOverviewResponse;
import org.coders.youmarket.services.dtos.user.ProfilePreviewResponse;
import org.modelmapper.ModelMapper;

public class EntityMapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    private EntityMapping() {
    }

    public static ProfilePreviewResponse userToProfilePreviewResponse(AppUser user) {
        return modelMapper.map(user, ProfilePreviewResponse.class);
    }

    public static ListingOverviewResponse userToListingOverviewResponse(Listing listing) {
        return modelMapper.map(listing, ListingOverviewResponse.class);
    }
}