package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.item.ItemListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemListingRepository extends JpaRepository<ItemListing ,Long> {
    Optional<ItemListing> findByListingReference(String reference);
}