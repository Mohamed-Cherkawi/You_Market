package org.coders.youmarket.entities.item;

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
import lombok.ToString;
import org.coders.youmarket.enums.listing.ListingConditionEnum;

@Getter @Setter @ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "item_properties")
public class ItemProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_properties_seq")
    @SequenceGenerator(name = "item_properties_seq" , allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category", nullable = false, length = 30)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, length = 13)
    private ListingConditionEnum condition;

    @Column(name = "brand", length = 99)
    private String brand;

    @Column(name = "sku", length = 99)
    private String sku;

}