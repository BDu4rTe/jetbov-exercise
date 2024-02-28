package com.jetbov.exercice.core.services;

import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;

import java.util.List;
import java.util.UUID;

public interface OxService {
    void create(CreateOx dto);
    List<Ox> getAll();
    Ox getById(UUID id);
    void update(UUID id, UpdateOx dto);
    void delete(UUID id);
}
