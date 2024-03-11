package com.jetbov.exercise.core.services;

import com.jetbov.exercise.core.dtos.CreateArea;
import com.jetbov.exercise.core.dtos.UpdateArea;
import com.jetbov.exercise.core.entities.Area;
import com.jetbov.exercise.infra.models.AreaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AreaService {
    void create(CreateArea dto);

    Page<AreaModel> getAll(Pageable pageable);

    Area getById(UUID id);

    void update(UUID id, UpdateArea dto);

    void delete(UUID id);
}