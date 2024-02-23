package com.jetbov.exercice.application;

import com.jetbov.exercice.adapters.AreaRepositoryService;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.core.services.AreaService;
import com.jetbov.exercice.infra.repositories.AreaRepository;

import java.util.List;
import java.util.UUID;

public class AreaServiceImpl implements AreaService {
    private AreaRepositoryService repository;

    @Override
    public void create(Area entity) {
        repository.createArea(entity);
    }

    @Override
    public List<Area> getAll() {
        return null;
    }

    @Override
    public Area getById(UUID id) {
        return null;
    }

    @Override
    public void update(UUID id, Area entity) {

    }

    @Override
    public void delete(UUID id) {

    }
}
