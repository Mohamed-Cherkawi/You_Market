package org.coders.youmarket.services.dtos.other;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressRequest {
    private Long id;
    private String title;
    private String description;
}