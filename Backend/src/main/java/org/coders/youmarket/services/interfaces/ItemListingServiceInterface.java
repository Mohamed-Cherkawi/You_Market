package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.services.dtos.listing.item.ItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ItemListingServiceInterface {
    ResponseEntity<Object> createItemListing(ItemRequest itemRequest , MultipartFile[] files);
    ResponseEntity<Object> updateItemListing(ItemRequest itemRequest);
}