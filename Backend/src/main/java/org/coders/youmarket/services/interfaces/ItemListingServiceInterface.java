package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.springframework.http.ResponseEntity;

public interface ItemListingServiceInterface {
    ResponseEntity<Object> createItemListing(ItemRequest itemRequest);
    ResponseEntity<Object> updateItemListing(ItemRequest itemRequest);
}