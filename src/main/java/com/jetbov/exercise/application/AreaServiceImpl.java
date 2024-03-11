package com.jetbov.exercise.application;

import com.jetbov.exercise.core.dtos.CreateArea;
import com.jetbov.exercise.core.dtos.UpdateArea;
import com.jetbov.exercise.core.entities.Area;
import com.jetbov.exercise.core.exceptions.EntityNotFound;
import com.jetbov.exercise.core.services.AreaService;
import com.jetbov.exercise.infra.models.AreaModel;
import com.jetbov.exercise.infra.repositories.AreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@AllArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;

    @Override
    public Page<AreaModel> getAll(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }

    @Override
    public Area getById(UUID id) {
        return areaRepository.findById(id).map(AreaModel::toEntity).orElseThrow(EntityNotFound::new);
    }

    @Override
    public void create(CreateArea dto) {
        var areaModel = new AreaModel();
        areaModel.fromCreateDto(dto);

        areaRepository.save(areaModel);
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
