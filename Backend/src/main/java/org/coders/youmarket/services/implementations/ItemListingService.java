package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.item.ItemListing;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.repositories.ItemListingRepository;
import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.coders.youmarket.services.interfaces.ItemListingServiceInterface;
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
public class ItemListingService implements ItemListingServiceInterface {
    private final ItemListingRepository itemListingRepository;

    @Override
    public ResponseEntity<Object> createItemListing(ItemRequest itemRequest) {
        ItemListing itemListing = EntityMapping.itemRequestToItemListing(itemRequest);

        itemListing.setListingReference(UUID.randomUUID().toString());
        itemListing.setListingType(ListingTypeEnum.ITEM);

        try {
            return ResponseHandler.generateResponse(
                    "The Item Listing Has Been Created Successfully",
                    HttpStatus.CREATED,
                    mapItemListingToItemRequest(itemListingRepository.save(itemListing),false)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse(
                    "The Item Listing Hasn't Created , Something went wrong please try again , and make sure all the required attributes are filled up",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateItemListing(ItemRequest itemRequest) {
        if(itemRequest.getListingReference() == null){
            return ResponseHandler.generateResponse(
                    "Can't updated your listing without a reference !",
                    HttpStatus.BAD_REQUEST);
        }
        ItemListing itemListing = itemListingRepository.findByListingReference(itemRequest.getListingReference())
                .orElse(null);

        if(itemListing == null){
            return ResponseHandler.generateResponse(
                    "There is no item listing with the given reference : " + itemRequest.getListingReference() + " to be updated !",
                    HttpStatus.BAD_REQUEST);
        }

        itemListing.setDescription(itemRequest.getDescription());
        itemListing.setPrice(Float.parseFloat(itemRequest.getPrice()));
        itemListing.setAssets(
                itemRequest.getAssets().stream()
                        .map(EntityMapping::photoRequestToPhoto)
                        .collect(Collectors.toSet())
        );
        itemListing.setLocation(
                EntityMapping.addressRequestToAddress(itemRequest.getLocation())
        );
        itemListing.setTitle(itemRequest.getTitle());
        itemListing.setDoorDropOff(
                Boolean.parseBoolean(itemRequest.getDoorDropOff())
        );
        itemListing.setDoorPickup(
                Boolean.parseBoolean(itemRequest.getDoorPickup())
        );
        itemListing.setPublicMeetup(
                Boolean.parseBoolean(itemRequest.getPublicMeetup())
        );
        itemListing.setProperties(
                EntityMapping.itemPropertiesRequestToItemProperties(itemRequest.getProperties())
        );
        itemListing.setUpdatedAt(LocalDateTime.now());

        try {
            return ResponseHandler.generateResponse(
                    "The Item Listing Has Been Updated Successfully",
                    HttpStatus.OK,
                    mapItemListingToItemRequest(itemListingRepository.save(itemListing),true));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.generateResponse(
                    "The Item Listing Hasn't Been Updated , Something went wrong please try again",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private ItemRequest mapItemListingToItemRequest(ItemListing itemListing , boolean setUpdatedAt){
        ItemRequest itemResponse = EntityMapping.itemListingToItemRequest(itemListing);

        if(setUpdatedAt) {
            itemResponse.setUpdatedAt(
                    DateTimeParser.getStringDateOfLocalDateTimeStandardPattern(itemListing.getUpdatedAt())
            );
        }

        return itemResponse;
    }
}