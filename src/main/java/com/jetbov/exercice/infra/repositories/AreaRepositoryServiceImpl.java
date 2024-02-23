package com.jetbov.exercice.infra.repositories;

import com.jetbov.exercice.adapters.AreaRepositoryService;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.infra.models.AreaModel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AreaRepositoryServiceImpl  implements AreaRepositoryService {
    private final AreaRepository repository;

    @Override
    public void createArea(Area entity) {
        AreaModel model = new AreaModel();
        model.fromEntity(entity);
        repository.save(model);
    }

    @Override
    public Area getById(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<Area> getAllAreas() {
        return null;
    }

    @Override
    public void updateArea(UUID id, Area entity) {

    }

    @Override
    public void deleteArea(UUID id) {

    }
}
