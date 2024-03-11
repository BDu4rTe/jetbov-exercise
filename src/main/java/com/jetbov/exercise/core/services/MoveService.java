package com.jetbov.exercise.core.services;

import com.jetbov.exercise.core.entities.Move;
import com.jetbov.exercise.infra.dtos.CreateMoveRequest;
import com.jetbov.exercise.infra.dtos.MoveFilter;
import com.jetbov.exercise.infra.models.MoveModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MoveService {
    Page<MoveModel> getAll(Pageable pageable);

   List<Move> searchWithFilter(MoveFilter filter);

   List<Move> getByEarrings(List<String> oxEarrings);

   void moveOx(UUID oxeId, CreateMoveRequest data);

}
