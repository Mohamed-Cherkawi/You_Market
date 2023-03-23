package org.coders.youmarket.entities.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.coders.youmarket.enums.vehicle.VehicleBodyStyleEnum;
import org.coders.youmarket.enums.vehicle.VehicleConditionEnum;
import org.coders.youmarket.enums.vehicle.VehicleFuelType;
import org.coders.youmarket.enums.vehicle.VehicleTypeEnum;

@Getter
@Setter
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "vehicle_properties")
public class VehicleProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_properties_seq")
    @SequenceGenerator(name = "vehicle_properties_seq" , allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "make", nullable = false, length = 70)
    private String make;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "mileage")
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 21)
    private VehicleTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_style", length = 11)
    private VehicleBodyStyleEnum bodyStyle;

    @Column(name = "has_clean_title")
    private Boolean hasCleanTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, length = 9)
    private VehicleConditionEnum condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false, length = 12)
    private VehicleFuelType fuelType;

    @Column(name = "is_manual")
    private Boolean isManual;

}