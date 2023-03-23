package org.coders.youmarket.services.dtos.listing.vehicle;

import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.enums.vehicle.VehicleBodyStyleEnum;
import org.coders.youmarket.enums.vehicle.VehicleConditionEnum;
import org.coders.youmarket.enums.vehicle.VehicleFuelType;
import org.coders.youmarket.enums.vehicle.VehicleTypeEnum;

@Getter @Setter
public class VehiclePropertiesRequest {
    private String id;
    private String make;
    private String model;
    private String mileage;
    private VehicleTypeEnum type;
    private VehicleBodyStyleEnum bodyStyle;
    private String hasCleanTitle;
    private VehicleConditionEnum condition;
    private VehicleFuelType fuelType;
    private String isManual;
}
