package com.jetbov.exercice.infra.repositories;

import com.jetbov.exercice.infra.models.MoveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MoveRepository extends JpaRepository<MoveModel, UUID> {
    int countByAreaName(String areaName);
}
