package com.jetbov.exercise.application;

import com.jetbov.exercise.core.dtos.CreateOx;
import com.jetbov.exercise.core.entities.Ox;
import com.jetbov.exercise.core.dtos.UpdateOx;
import com.jetbov.exercise.core.exceptions.EntityNotFound;
import com.jetbov.exercise.core.services.OxService;
import com.jetbov.exercise.infra.models.OxModel;
import com.jetbov.exercise.infra.repositories.OxRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@AllArgsConstructor
public class OxServiceImpl implements OxService {
    private final OxRepository oxRepository;

    @Override
    public Page<OxModel> getAll(Pageable pageable) {
        return oxRepository.findAll(pageable);
    }

    @Override
    public Ox getById(UUID id) {
        var ox = oxRepository.findById(id).orElseThrow(
                () -> new EntityNotFound(Ox.class, "id", id)
        );
        return ox.toEntity();
    }

    @Override
    public void create(CreateOx dto) {
        var oxModel = new OxModel();
        oxModel.fromCreateDto(dto);

        oxRepository.save(oxModel);
    }


    @Override
    public void update(UUID id, UpdateOx dto) {
        var oxModel = oxRepository.findById(id).orElseThrow(
                () -> new EntityNotFound(Ox.class, "id", id)
        );
        BeanUtils.copyProperties(oxModel, dto, ServiceHelper.getNullPropertyNames(dto));
        oxRepository.save(oxModel);
    }

    @Override
    public void delete(UUID id) {
        if (oxRepository.existsById(id)) {
            oxRepository.deleteById(id);
        }
    }
}
