package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coders.youmarket.entities.Photo;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemListingService implements ItemListingServiceInterface {
    private final ItemListingRepository itemListingRepository;

    @Override
    public ResponseEntity<Object> createItemListing(ItemRequest itemRequest , MultipartFile[] files) {
        ItemListing itemListing = EntityMapping.itemRequestToItemListing(itemRequest);

        itemListing.setListingReference(UUID.randomUUID().toString());
        itemListing.setListingType(ListingTypeEnum.ITEM);

        try {
            Set<Photo> photos = processImages(files);
            log.info("Assets Processed From incoming files : {}",photos);
            itemListing.setAssets(photos);
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

    private Set<Photo> processImages(MultipartFile[] files) throws IOException {
        Set<Photo> photos = new HashSet<>();
        for (MultipartFile file : files){
            Photo imageModel = Photo.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .picByte(file.getBytes())
                    .build();

            photos.add(imageModel);
        }

        return photos;
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
        itemListing.setPrice(itemRequest.getPrice());
        itemListing.setAssets(
                itemRequest.getAssets().stream()
                        .map(EntityMapping::photoRequestToPhoto)
                        .collect(Collectors.toSet())
        );
        itemListing.setLocation(
                EntityMapping.addressRequestToAddress(itemRequest.getLocation())
        );
        itemListing.setTitle(itemRequest.getTitle());
        itemListing.setDoorDropOff(itemRequest.getDoorDropOff());
        itemListing.setDoorPickup(itemRequest.getDoorPickup());
        itemListing.setPublicMeetup(itemRequest.getPublicMeetup());
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