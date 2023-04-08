package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.coders.youmarket.services.interfaces.ItemListingServiceInterface;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/listing/item")
@RequiredArgsConstructor
public class ItemListingController {

    private final ItemListingServiceInterface itemListingService;

    @PostMapping(value = "/creating" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> createItemListingApi(@RequestPart("item") ItemRequest itemRequest ,
                                                       @RequestPart("imagesFiles")MultipartFile[] files) throws IOException {
        return itemListingService.createItemListing(itemRequest , files);
    }

    @PutMapping("/updating")
    public ResponseEntity<Object> updateItemListingApi(@RequestBody ItemRequest itemRequest){
        return itemListingService.updateItemListing(itemRequest);
    }

}