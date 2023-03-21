package org.coders.youmarket.entities.item;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.coders.youmarket.entities.Address;
import org.coders.youmarket.entities.Listing;

@Getter @Setter
@Entity
@NoArgsConstructor @AllArgsConstructor @SuperBuilder
@Table(name = "item_listing")
public class ItemListing extends Listing {
    @Column(name = "title", nullable = false, length = 99)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Address location;

    @Column(name = "public_meetup")
    private Boolean publicMeetup;

    @Column(name = "door_pickup")
    private Boolean doorPickup;

    @Column(name = "door_drop_off")
    private Boolean doorDropOff;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "properties_id", nullable = false)
    private ItemProperties properties;

}