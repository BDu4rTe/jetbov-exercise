package com.jetbov.exercice.core.services;

import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;
import com.jetbov.exercice.infra.dtos.MoveOxDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OxService {
    List<Ox> getAll(Pageable pageable);

    Ox getById(UUID id);

    void create(CreateOx dto);

    void move(UUID oxId, MoveOxDto dto);

    void update(UUID id, UpdateOx dto);

    void delete(UUID id);
}
