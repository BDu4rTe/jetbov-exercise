package com.jetbov.exercice.adapters;

import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.dtos.UpdateArea;
import com.jetbov.exercice.core.entities.Area;

import java.util.List;
import java.util.UUID;

public interface AreaGateway {
    void createArea(CreateArea dto);
    Area getById(UUID id);
    List<Area> getAllAreas();
    void updateArea(UUID id, UpdateArea dto);
    void deleteArea(UUID id);
}
