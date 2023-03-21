package org.coders.youmarket.entities.vehicle;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.coders.youmarket.entities.Listing;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @SuperBuilder
@PrimaryKeyJoinColumn(name = "listingId")
public class VehicleListing extends Listing {

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "vehicle_properties_id", nullable = false)
    private VehicleProperties properties;

}