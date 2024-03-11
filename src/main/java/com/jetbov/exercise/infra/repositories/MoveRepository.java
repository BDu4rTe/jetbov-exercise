package com.jetbov.exercise.infra.repositories;

import com.jetbov.exercise.infra.models.MoveModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MoveRepository extends JpaRepository<MoveModel, UUID> {
    int countByAreaName(String areaName);
    List<MoveModel> findByAreaNameOrOxEarring(String areaName, String oxEarring);

    List<MoveModel> findByOxEarringIn(List<String> oxenEarrings);
}
