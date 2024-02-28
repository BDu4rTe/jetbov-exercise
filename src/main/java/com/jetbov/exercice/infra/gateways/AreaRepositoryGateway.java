package com.jetbov.exercice.infra.gateways;

import com.jetbov.exercice.adapters.AreaGateway;
import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.dtos.UpdateArea;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.core.exceptions.EntityNotFound;
import com.jetbov.exercice.infra.GatewayHelper;
import com.jetbov.exercice.infra.models.AreaModel;
import com.jetbov.exercice.infra.repositories.AreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AreaRepositoryGateway implements AreaGateway {
    private final AreaRepository repository;

    @Override
    public void createArea(CreateArea dto) {
        AreaModel model = new AreaModel();
        model.fromCreateDto(dto);
        repository.save(model);
    }

    @Override
    public Area getAreaById(UUID id) {
        var search = repository.findById(id);
        return search.map(AreaModel::toEntity).orElseThrow(EntityNotFound::new);
    }

    @Override
    public List<Area> getAllAreas() {
        return repository.findAll().stream().map(AreaModel::toEntity).toList();
    }

    @Override
    public void updateArea(UUID id, UpdateArea dto) {
        var areaModel = repository.findById(id).orElseThrow(EntityNotFound::new);
        BeanUtils.copyProperties(dto, areaModel, GatewayHelper.getNullPropertyNames(dto));
        repository.save(areaModel);
    }

    @Override
    public void deleteArea(UUID id) {
        repository.findById(id).orElseThrow(EntityNotFound::new);
        repository.deleteById(id);
    }
}
