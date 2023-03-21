package org.coders.youmarket.services.dtos.listing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter @Setter @ToString
public class ListingOverviewResponse {
    private String listingReference;
    private Set<String> assets;
    private Float price;
    private String description;
}