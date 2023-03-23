package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.coders.youmarket.services.interfaces.ItemListingServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listing/item")
@RequiredArgsConstructor
public class ItemListingController {

    private final ItemListingServiceInterface itemListingService;

    @PostMapping("/creating")
    public ResponseEntity<Object> createVehicleListingApi(@RequestBody ItemRequest itemRequest){
        return itemListingService.createItemListing(itemRequest);
    }

    @PutMapping("/updating")
    public ResponseEntity<Object> updateVehicleListingApi(@RequestBody ItemRequest itemRequest){
        return itemListingService.updateItemListing(itemRequest);
    }
}