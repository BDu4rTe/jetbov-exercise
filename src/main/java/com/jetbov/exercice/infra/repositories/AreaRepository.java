package com.jetbov.exercice.infra.repositories;

import com.jetbov.exercice.infra.models.AreaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<AreaModel, UUID> {
}
