package org.coders.youmarket.entities.home;

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
import org.coders.youmarket.enums.home.AirConditioningTypeEnum;
import org.coders.youmarket.enums.home.HeatingTypeEnum;
import org.coders.youmarket.enums.home.LaundryTypeEnum;
import org.coders.youmarket.enums.home.ParkingTypeEnum;
import org.coders.youmarket.enums.home.RentalTypeEnum;

@Getter @Setter @Entity
@Builder @AllArgsConstructor @NoArgsConstructor
@Table(name = "home_rental_properties")
public class HomeRentalProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "home_rental_properties_seq")
    @SequenceGenerator(name = "home_rental_properties_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_type", nullable = false, length = 13)
    private RentalTypeEnum rentalType;

    @Column(name = "number_of_bed_rooms")
    private Byte numberOfBedRooms;

    @Column(name = "number_of_bath_rooms")
    private Byte numberOfBathRooms;

    @Enumerated(EnumType.STRING)
    @Column(name = "laundry_type", length = 19)
    private LaundryTypeEnum laundryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "parking_type", length = 17)
    private ParkingTypeEnum parkingType;

    @Enumerated(EnumType.STRING)
    @Column(name = "air_conditioning_type", length = 12)
    private AirConditioningTypeEnum airConditioningType;

    @Enumerated(EnumType.STRING)
    @Column(name = "heating_type", length = 17)
    private HeatingTypeEnum heatingType;

    @Column(name = "is_cat_friendly")
    private Boolean isCatFriendly;

    @Column(name = "is_dog_friendly")
    private Boolean isDogFriendly;
}