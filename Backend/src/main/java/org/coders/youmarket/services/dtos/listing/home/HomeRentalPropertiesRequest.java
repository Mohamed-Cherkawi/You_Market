package org.coders.youmarket.services.dtos.listing.home;

import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.enums.home.AirConditioningTypeEnum;
import org.coders.youmarket.enums.home.HeatingTypeEnum;
import org.coders.youmarket.enums.home.LaundryTypeEnum;
import org.coders.youmarket.enums.home.ParkingTypeEnum;
import org.coders.youmarket.enums.home.RentalTypeEnum;

@Getter @Setter
public class HomeRentalPropertiesRequest {
    private Long id;
    private RentalTypeEnum rentalType;
    private Byte numberOfBedRooms;
    private Byte numberOfBathRooms;
    private LaundryTypeEnum laundryType;
    private ParkingTypeEnum parkingType;
    private AirConditioningTypeEnum airConditioningType;
    private HeatingTypeEnum heatingType;
    private Boolean isCatFriendly;
    private Boolean isDogFriendly;
}