package org.coders.youmarket.services.dtos.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.coders.youmarket.services.dtos.other.AddressRequest;

@Getter @Setter @ToString
public class ProfilePreviewResponse {
    private String username;
    private String name;
    private String phone;
    private AddressRequest address;
    private String profilePhoto;
}