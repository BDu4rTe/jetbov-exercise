package com.jetbov.exercice.application;

import com.jetbov.exercice.core.entities.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.entities.UpdateOx;
import com.jetbov.exercice.core.services.OxService;

import java.util.List;
import java.util.UUID;

public class OxServiceImpl implements OxService {
    @Override
    public void create(CreateOx dto) {

    }

    @Override
    public List<Ox> getAll() {
        return null;
    }

    @Override
    public Ox getById(UUID id) {
        return null;
    }

    @Override
    public void update(UUID id, UpdateOx dto) {
//        var ox =
    }

    @Override
    public void delete(UUID id) {

    }
}
