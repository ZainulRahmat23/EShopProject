package com.miniProject.shop.Repository;

import com.miniProject.shop.model.ShipVia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipViaRepository extends JpaRepository<ShipVia, Integer> {

    Optional<ShipVia> findAllByCompanyName(String companyName);
}
