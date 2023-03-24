package org.coders.youmarket.services.dtos.other;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PhotoRequest {
    private Long id;
    private String imageUrl;
}