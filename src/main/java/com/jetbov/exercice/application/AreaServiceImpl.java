package com.jetbov.exercice.application;

import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.dtos.UpdateArea;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.core.exceptions.EntityNotFound;
import com.jetbov.exercice.core.services.AreaService;
import com.jetbov.exercice.infra.models.AreaModel;
import com.jetbov.exercice.infra.repositories.AreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;

    @Override
    public void create(CreateArea dto) {
        var areaModel = new AreaModel();
        areaModel.fromCreateDto(dto);

        areaRepository.save(areaModel);
    }

    @Override
    public List<Area> getAll() {
        var areaModels = areaRepository.findAll();
        return areaModels.stream().map(AreaModel::toEntity).toList();
    }

    @Override
    public Area getById(UUID id) {
        return areaRepository.findById(id).map(AreaModel::toEntity).orElseThrow(EntityNotFound::new);
    }

    @Override
    public void update(UUID id, UpdateArea dto) {
        var areaModel = areaRepository.findById(id).orElseThrow(EntityNotFound::new);
        BeanUtils.copyProperties(areaModel, dto, ServiceHelper.getNullPropertyNames(dto));
        areaRepository.save(areaModel);
    }

    @Override
    public void delete(UUID id) {
        if (areaRepository.existsById(id)){
            areaRepository.deleteById(id);
        }
    }
}
