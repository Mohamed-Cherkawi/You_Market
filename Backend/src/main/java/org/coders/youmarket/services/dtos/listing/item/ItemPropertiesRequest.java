package org.coders.youmarket.services.dtos.listing.item;

import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.enums.listing.ListingConditionEnum;

@Getter @Setter
public class ItemPropertiesRequest {
    private String id;
    private String category;
    private ListingConditionEnum condition;
    private String brand;
    private String sku;
}