package com.jetbov.exercise.application;

import com.jetbov.exercise.core.entities.Move;
import com.jetbov.exercise.core.exceptions.EntityNotFound;
import com.jetbov.exercise.core.services.MoveService;
import com.jetbov.exercise.infra.dtos.CreateMoveRequest;
import com.jetbov.exercise.infra.dtos.MoveFilter;
import com.jetbov.exercise.infra.models.MoveModel;
import com.jetbov.exercise.infra.repositories.AreaRepository;
import com.jetbov.exercise.infra.repositories.MoveRepository;
import com.jetbov.exercise.infra.repositories.OxRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
public class MoveServiceImpl implements MoveService {
    private final MoveRepository moveRepository;
    private final OxRepository oxRepository;
    private final AreaRepository areaRepository;

    public Page<MoveModel> getAll(Pageable pageable) {
        return moveRepository.findAll(pageable);
    }

    @Override
    public List<Move> searchWithFilter(MoveFilter filter) {
        var moveModels = moveRepository.findByAreaNameOrOxEarring(filter.areaName(), filter.oxEarring());
        return moveModels.stream().map(MoveModel::toEntity).toList();
    }

    @Override
    public List<Move> getByEarrings(List<String> oxEarrings) {
        var moveModels = moveRepository.findByOxEarringIn(oxEarrings);
        return moveModels.stream().map(MoveModel::toEntity).toList();
    }

    @Override
    public void moveOx(UUID oxId, CreateMoveRequest data) {
        var ox = oxRepository.findById(oxId).orElseThrow(EntityNotFound::new);
        var area = areaRepository.findById(data.areaId()).orElseThrow(EntityNotFound::new);

        if (area.getOxesOnArea().contains(ox)) {
            System.out.println("boi já esta na area.");
            return;
        }

        var oxesOnArea = moveRepository.countByAreaName(area.getName());
        System.out.println(oxesOnArea);

        if (oxesOnArea < area.getMaxCapacity()) {
            var oldOxWeight = ox.getWeight();
            var weightGain = area.getGmd() * data.allocatedPeriod();
            var newWeight = oldOxWeight + weightGain;

            ox.setWeight(newWeight);
            ox.setCurrentArea(area);
            oxRepository.save(ox);

            var move = new MoveModel();
            move.setAreaName(area.getName());
            move.setOxEarring(ox.getEarring());
            move.setEntryWeight(oldOxWeight);
            move.setOutWeight(newWeight);
            move.setAllocatedPeriod(data.allocatedPeriod());

            moveRepository.save(move);
        } else {
            System.out.println("Area lotada");
        }


    }

}
