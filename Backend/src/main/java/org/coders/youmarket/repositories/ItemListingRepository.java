package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.item.ItemListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemListingRepository extends JpaRepository<ItemListing ,Long> {
}