package org.coders.youmarket.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.coders.youmarket.enums.VehicleTypeEnum;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "listingId")
public class VehicleListing extends Listing{
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 21)
    private VehicleTypeEnum type;
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;
    @Column(name = "price", nullable = false)
    private Float price;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "vehicle_properties_id", nullable = false)
    private VehicleProperties vehicleProperties;
}