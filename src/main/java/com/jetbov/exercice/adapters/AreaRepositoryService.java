package com.jetbov.exercice.adapters;

import com.jetbov.exercice.core.entities.Area;

import java.util.List;
import java.util.UUID;

public interface AreaRepositoryService {
    public void createArea(Area entity);
    public Area getById(UUID id) throws Exception;
    public List<Area> getAllAreas();
    public void updateArea(UUID id, Area entity);
    public void deleteArea(UUID id);
}
