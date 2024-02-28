package com.jetbov.exercice.adapters;

import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;

import java.util.List;
import java.util.UUID;

public interface OxGateway {
    void createOx(CreateOx dto);
    Ox getById(UUID id);
    List<Ox> getAll();
    void update(UUID id, UpdateOx dto);
    void delete(UUID id);
}
