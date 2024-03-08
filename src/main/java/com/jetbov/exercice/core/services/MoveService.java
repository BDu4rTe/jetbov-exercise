package com.jetbov.exercice.core.services;

import com.jetbov.exercice.core.entities.Move;

import java.util.List;
import java.util.UUID;

public interface MoveService {
    List<Move> getAll();

   Move getById(UUID id);

   Move getByOxId(UUID oxId);


}
