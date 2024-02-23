package com.jetbov.exercice.infra.repositories;

import com.jetbov.exercice.adapters.OxRepositoryService;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.entities.UpdateOx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.UUID;

public class OxRepositoryServiceImpl implements OxRepositoryService {
    @Autowired
    OxRepository repository;

    @Override
    public void createOx(Ox entity) {

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
