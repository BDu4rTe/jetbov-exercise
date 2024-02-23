package com.jetbov.exercice.infra.repositories;

import com.jetbov.exercice.infra.models.OxModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OxRepository extends JpaRepository<OxModel, UUID> {
}
