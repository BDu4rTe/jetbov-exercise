package com.jetbov.exercice.adapters;

import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.dtos.MoveOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;

import java.util.List;
import java.util.UUID;

public interface OxGateway {
    Ox getOxById(UUID id);

    List<Ox> getAllOxes();

    void createOx(CreateOx dto);

    void moveOx(UUID oxId, MoveOx data);

    void updateOx(UUID id, UpdateOx dto);

    void deleteOx(UUID id);
}
