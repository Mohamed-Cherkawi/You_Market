package org.coders.youmarket.configuration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.Address;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.entities.Listing;
import org.coders.youmarket.entities.Photo;
import org.coders.youmarket.entities.item.ItemListing;
import org.coders.youmarket.entities.item.ItemProperties;
import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.coders.youmarket.entities.vehicle.VehicleProperties;
import org.coders.youmarket.enums.AvailabilityStateEnum;
import org.coders.youmarket.enums.item.ListingCategoryEnum;
import org.coders.youmarket.enums.listing.ListingConditionEnum;
import org.coders.youmarket.enums.listing.ListingTypeEnum;
import org.coders.youmarket.enums.vehicle.VehicleBodyStyleEnum;
import org.coders.youmarket.enums.vehicle.VehicleConditionEnum;
import org.coders.youmarket.enums.vehicle.VehicleFuelType;
import org.coders.youmarket.enums.vehicle.VehicleTypeEnum;
import org.coders.youmarket.repositories.ListingRepository;
import org.coders.youmarket.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Configuration @RequiredArgsConstructor
public class Data {
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner insertSomeData() {
        AppUser user = AppUser.builder().
                reference(UUID.randomUUID().toString()).
                username("Simox").
                password(passwordEncoder.encode("pass123")).
                name("Mohamed").
                status(AvailabilityStateEnum.ONLINE).
                phone("0618387383")
                .profilePhoto("image.png")
                .address(Address.builder().
                        title("hed Swalm").
                        description("test").build()
                )
                .createdAt(LocalDateTime.now()).build();

        Listing vehicle = VehicleListing.builder()
                .listingReference(UUID.randomUUID().toString())
                .ownerReference(UUID.randomUUID().toString())
                .description("one of the best german products ...")
                .assets(Set.of(
                        Photo.builder().name("product.jpeg").build()
                ))
                .purchaseDate(LocalDate.of(2002, Month.NOVEMBER,7))
                .price(50F)
                .listingType(ListingTypeEnum.VEHICLE)
                .properties(VehicleProperties.builder()
                                .make("FORD")
                                .model("Fiesta")
                                .type(VehicleTypeEnum.CAR_TRUCK)
                                .bodyStyle(VehicleBodyStyleEnum.SMALL_CAR)
                                .condition(VehicleConditionEnum.GOOD)
                                .fuelType(VehicleFuelType.DIESEL)
                                .isManual(true)
                                .build()
                )
                .build();
        Listing item = ItemListing.builder()
                .listingReference(UUID.randomUUID().toString())
                .ownerReference(UUID.randomUUID().toString())
                .description("This phone is good")
                .assets(Set.of(
                        Photo.builder().name("phone.jpg").build()
                ))
                .title("Iphone X")
                .location(
                        Address.builder().title("Marrakech").description("Jnan Awrad").build()
                )
                .price(200F)
                .listingType(ListingTypeEnum.ITEM)
                .properties(
                        ItemProperties.builder()
                                .category(ListingCategoryEnum.Electronics.MOBILE_PHONES.name())
                                .brand("Apple")
                                .condition(ListingConditionEnum.NEW)
                                .build()
                )
                .build();
        return args -> {
            userRepository.save(user);
            listingRepository.saveAll(List.of(vehicle,item));
        };
    }
}