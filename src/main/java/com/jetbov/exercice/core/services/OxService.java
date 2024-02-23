package com.jetbov.exercice.core.services;

import com.jetbov.exercice.core.entities.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.entities.UpdateOx;

import java.util.List;
import java.util.UUID;

public interface OxService {
    public void create(CreateOx dto);
    public List<Ox> getAll();
    public Ox getById(UUID id);
    public void update(UUID id, UpdateOx dto);
    public void delete(UUID id);
}
