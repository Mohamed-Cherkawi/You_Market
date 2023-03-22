package org.coders.youmarket.util;

import org.coders.youmarket.entities.Photo;

import java.util.Set;
import java.util.stream.Collectors;

public class AssetsMapper {
    
    private AssetsMapper(){}

    public static Set<String> mapSetOfPhotosToSetOfStrings(Set<Photo> assets){
        return assets.stream()
                .map(Photo::getImageUrl)
                .collect(Collectors.toSet());
    }
    public static Set<Photo> mapSetOfStringsToSetOfPhotos(Set<String> imagesUrls){
        return imagesUrls.stream()
                .map(imageUrl -> Photo.builder()
                        .imageUrl(imageUrl)
                        .build())
                .collect(Collectors.toSet());
    }
}
