package com.jetbov.exercise.infra.repositories;

import com.jetbov.exercise.infra.models.OxModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OxRepository extends JpaRepository<OxModel, UUID> {
}
