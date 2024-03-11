package com.jetbov.exercise.infra.repositories;

import com.jetbov.exercise.infra.models.AreaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<AreaModel, UUID> {
}
