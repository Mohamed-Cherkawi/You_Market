package org.coders.youmarket.entities.home;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.coders.youmarket.entities.Address;
import org.coders.youmarket.entities.Listing;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "home_rental_listing")
public class HomeRentalListing extends Listing {
    @Column(name = "price_per_month", nullable = false)
    private Float pricePerMonth;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "available_at", nullable = false)
    private LocalDateTime availableAt;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "properties_id", nullable = false)
    private HomeRentalProperties properties;

}