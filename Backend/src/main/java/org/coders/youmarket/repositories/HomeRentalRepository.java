package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.home.HomeRentalListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRentalRepository extends JpaRepository<HomeRentalListing,Long> {
    Optional<HomeRentalListing> findByListingReference(String reference);
}