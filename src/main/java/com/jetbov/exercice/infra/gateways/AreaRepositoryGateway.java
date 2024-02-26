package com.jetbov.exercice.infra.gateways;

import com.jetbov.exercice.adapters.AreaGateway;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.infra.models.AreaModel;
import com.jetbov.exercice.infra.repositories.AreaRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AreaRepositoryServiceImpl  implements AreaGateway {
    private final AreaRepository repository;

    @Override
    public void createArea(Area entity) {
        AreaModel model = new AreaModel();
        model.fromEntity(entity);
        repository.save(model);
    }

    @Override
    public Area getById(UUID id) {
        var search = repository.findById(id);

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
