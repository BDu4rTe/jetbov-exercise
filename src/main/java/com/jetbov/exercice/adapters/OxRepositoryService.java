package com.jetbov.exercice.adapters;

import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.entities.UpdateOx;
import org.hibernate.sql.Update;

import java.util.List;
import java.util.UUID;

public interface OxRepositoryService {
    public void createOx(Ox entity);
    public Ox getById(UUID id);
    public List<Ox> getAll();
    public void update(UUID id, UpdateOx dto);
    public void delete(UUID id);
}
