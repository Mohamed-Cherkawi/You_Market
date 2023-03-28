package org.coders.youmarket.services.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.coders.youmarket.services.dtos.other.AddressRequest;

@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String name;
    private String phone;
    private AddressRequest address;
}