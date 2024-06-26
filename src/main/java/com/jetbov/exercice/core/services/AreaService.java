package com.jetbov.exercice.core.services;

import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.dtos.UpdateArea;
import com.jetbov.exercice.core.entities.Area;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AreaService {
    void create(CreateArea dto);

    List<Area> getAll(Pageable pageable);

    Area getById(UUID id);

    void update(UUID id, UpdateArea dto);

    void delete(UUID id);
}