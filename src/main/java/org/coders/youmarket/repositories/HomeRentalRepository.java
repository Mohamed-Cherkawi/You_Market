package org.coders.youmarket.repositories;

import org.coders.youmarket.entities.home.HomeRentalListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRentalRepository extends JpaRepository<HomeRentalListing,Long> {}