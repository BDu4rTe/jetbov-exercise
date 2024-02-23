package com.jetbov.exercice.core.services;

import com.jetbov.exercice.core.entities.Area;

import java.util.List;
import java.util.UUID;

public interface AreaService {
    public void create(Area entity);
    public List<Area> getAll();
    public Area getById(UUID id);
    public void update(UUID id, Area entity);
    public void delete(UUID id);
}