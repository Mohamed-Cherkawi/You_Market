package org.coders.youmarket.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder @AllArgsConstructor @Getter
public class HeaderKeyValueResponse {
    private String key;
    private String value;
}