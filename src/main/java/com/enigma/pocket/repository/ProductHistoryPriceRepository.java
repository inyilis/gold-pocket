package com.enigma.pocket.repository;

import com.enigma.pocket.entity.ProductHistoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryPriceRepository extends JpaRepository<ProductHistoryPrice, String> {
}
