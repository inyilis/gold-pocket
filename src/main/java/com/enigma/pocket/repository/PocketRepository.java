package com.enigma.pocket.repository;

import com.enigma.pocket.entity.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketRepository extends JpaRepository<Pocket, String> {
}
