package org.coders.youmarket.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    private String ownerReference;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "listing_id")
    private Set<Photo> assets = new LinkedHashSet<>();

    private String description;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}