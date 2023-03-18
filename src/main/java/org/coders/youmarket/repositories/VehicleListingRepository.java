package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.VehicleListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleListingRepository extends JpaRepository<VehicleListing,Long> { }