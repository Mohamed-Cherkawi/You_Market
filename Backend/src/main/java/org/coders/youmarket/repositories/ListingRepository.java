package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    Optional<Listing> findByListingReference(String reference);
    List<Listing> findAllByOwnerReference(String ownerReference);
}