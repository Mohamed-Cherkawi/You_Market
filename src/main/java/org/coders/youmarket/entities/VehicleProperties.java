package org.coders.youmarket.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.enums.VehicleBodyStyleEnum;
import org.coders.youmarket.enums.VehicleConditionEnum;
import org.coders.youmarket.enums.VehicleFuelType;

@Getter
@Setter
@Entity
@Table(name = "vehicle_properties")
public class VehicleProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_properties_seq")
    @SequenceGenerator(name = "vehicle_properties_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "make", nullable = false, length = 70)
    private String make;
    @Column(name = "model", nullable = false, length = 100)
    private String model;
    @Column(name = "mileage")
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_style", nullable = false, length = 11)
    private VehicleBodyStyleEnum bodyStyle;

    @Column(name = "has_clean_title")
    private Boolean hasCleanTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, length = 9)
    private VehicleConditionEnum condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false, length = 12)
    private VehicleFuelType fuelType;

    @Column(name = "is_manual", nullable = false)
    private Boolean isManual;

}