package com.jetbov.exercice.application;

import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;
import com.jetbov.exercice.core.exceptions.EntityNotFound;
import com.jetbov.exercice.core.services.OxService;
import com.jetbov.exercice.infra.dtos.MoveOxDto;
import com.jetbov.exercice.infra.models.MoveModel;
import com.jetbov.exercice.infra.models.OxModel;
import com.jetbov.exercice.infra.repositories.AreaRepository;
import com.jetbov.exercice.infra.repositories.MoveRepository;
import com.jetbov.exercice.infra.repositories.OxRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class OxServiceImpl implements OxService {
    private final OxRepository oxRepository;
    private final AreaRepository areaRepository;
    private final MoveRepository moveRepository;

    @Override
    public List<Ox> getAll() {
        return oxRepository.findAll().stream().map(OxModel::toEntity).toList();
    }

    @Override
    public Ox getById(UUID id) {
        var ox = oxRepository.findById(id).orElseThrow(EntityNotFound::new);
        return ox.toEntity();
    }

    @Override
    public void create(CreateOx dto) {
        var oxModel = new OxModel();
        oxModel.fromCreateDto(dto);

        oxRepository.save(oxModel);
    }

    @Override
    public void move(UUID oxId, MoveOxDto data) {
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

            var move = MoveModel.builder()
                    .areaName(area.getName())
                    .allocatedPeriod(data.allocatedPeriod())
                    .oxEarring(ox.getEarring())
                    .entryHeight(oldOxWeight)
                    .outHeight(newWeight)
                    .build();

            moveRepository.save(move);
        }


    }

    @Override
    public void update(UUID id, UpdateOx dto) {
        var oxModel = oxRepository.findById(id).orElseThrow(EntityNotFound::new);
        BeanUtils.copyProperties(oxModel, dto, ServiceHelper.getNullPropertyNames(dto));
        oxRepository.save(oxModel);
    }

    @Override
    public void delete(UUID id) {
        if (oxRepository.existsById(id)) {
            oxRepository.deleteById(id);
        }
    }
}
