package com.jetbov.exercice.infra.gateways;

import com.jetbov.exercice.adapters.OxGateway;
import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;
import com.jetbov.exercice.core.exceptions.EntityNotFound;
import com.jetbov.exercice.infra.GatewayHelper;
import com.jetbov.exercice.infra.models.OxModel;
import com.jetbov.exercice.infra.repositories.OxRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class OxRepositoryGateway implements OxGateway {
    private final OxRepository repository;
    private final GatewayHelper helper;

    @Override
    public void createOx(CreateOx dto) {
        var oxModel = new OxModel();
        oxModel.fromCreateDto(dto);
        repository.save(oxModel);
    }

    @Override
    public Ox getById(UUID id) {
        return repository.findById(id).map(OxModel::toEntity).orElseThrow(EntityNotFound::new);
    }

    @Override
    public List<Ox> getAll() {
        return repository.findAll().stream().map(OxModel::toEntity).toList();
    }

    @Override
    public void update(UUID id, UpdateOx dto) {
        var oxModel = repository.findById(id).orElseThrow(EntityNotFound::new);
        BeanUtils.copyProperties(oxModel, dto, helper.getNullPropertyNames(dto));
        repository.save(oxModel);
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).orElseThrow(EntityNotFound::new);
        repository.deleteById(id);

    }
}
