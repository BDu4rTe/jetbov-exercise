package com.jetbov.exercise.core.services;

import com.jetbov.exercise.core.dtos.CreateOx;
import com.jetbov.exercise.core.entities.Ox;
import com.jetbov.exercise.core.dtos.UpdateOx;
import com.jetbov.exercise.infra.models.OxModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OxService {
    Page<OxModel> getAll(Pageable pageable);

    Ox getById(UUID id);

    void create(CreateOx dto);


    void update(UUID id, UpdateOx dto);

    void delete(UUID id);
}
