package com.jetbov.exercice.infra.gateways;

import com.jetbov.exercice.adapters.OxGateway;
import com.jetbov.exercice.core.entities.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.entities.UpdateOx;
import com.jetbov.exercice.infra.repositories.OxRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class OxRepositoryServiceImpl implements OxGateway {
    @Autowired
    OxRepository repository;

    @Override
    public void createOx(CreateOx entity) {

    }

    @Override
    public Ox getById(UUID id) {
        return null;
    }

    @Override
    public List<Ox> getAll() {
        return null;
    }

    @Override
    public void update(UUID id, UpdateOx dto) {
        var oldOx = repository.findById(id).orElseThrow();
    }

    @Override
    public void delete(UUID id) {

    }
}
