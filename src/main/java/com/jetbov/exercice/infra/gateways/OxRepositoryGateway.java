package com.jetbov.exercice.infra.gateways;

import com.jetbov.exercice.adapters.OxGateway;
import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.dtos.MoveOx;
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


    @Override
    public Ox getOxById(UUID id) {
        return repository.findById(id).map(OxModel::toEntity).orElseThrow(EntityNotFound::new);
    }

    @Override
    public List<Ox> getAllOxes() {
        return repository.findAll().stream().map(OxModel::toEntity).toList();
    }

    @Override
    public void createOx(CreateOx dto) {
        var oxModel = new OxModel();
        oxModel.fromCreateDto(dto);
        repository.save(oxModel);
    }

    @Override
    public void moveOx(UUID oxId, MoveOx data) {
        var oxSearch = repository.findById(oxId).orElseThrow(EntityNotFound::new);
        var areaSearch = repository.findById(data.areaId()).orElseThrow(EntityNotFound::new);
    }

    @Override
    public void updateOx(UUID id, UpdateOx dto) {
        var oxModel = repository.findById(id).orElseThrow(EntityNotFound::new);
        BeanUtils.copyProperties(oxModel, dto, GatewayHelper.getNullPropertyNames(dto));
        repository.save(oxModel);
    }

    @Override
    public void deleteOx(UUID id) {
        repository.findById(id).orElseThrow(EntityNotFound::new);
        repository.deleteById(id);

    }
}
