package org.coders.youmarket.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.coders.youmarket.enums.listing.ListingTypeEnum;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor @NoArgsConstructor @SuperBuilder
@Getter @Setter @ToString
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "listing_reference", nullable = false, length = 100)
    private String listingReference;

    @Column(name = "owner_reference", nullable = false, length = 100)
    private String ownerReference;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "listing_id")
    private Set<Photo> assets = new LinkedHashSet<>();

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "listing_type", nullable = false, length = 7)
    private ListingTypeEnum listingType;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}