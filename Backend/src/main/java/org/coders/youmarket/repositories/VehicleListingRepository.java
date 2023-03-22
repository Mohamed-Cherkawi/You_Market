package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.vehicle.VehicleListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleListingRepository extends JpaRepository<VehicleListing,Long> {
    Optional<VehicleListing> findByListingReference(String reference);
}