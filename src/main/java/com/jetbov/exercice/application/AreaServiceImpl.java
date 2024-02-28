package com.jetbov.exercice.application;

import com.jetbov.exercice.adapters.AreaGateway;
import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.dtos.UpdateArea;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.core.services.AreaService;

import java.util.List;
import java.util.UUID;

public class AreaServiceImpl implements AreaService {
    private final AreaGateway gateway;

    public AreaServiceImpl(AreaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void create(CreateArea dto) {
        gateway.createArea(dto);
    }

    @Override
    public List<Area> getAll() {
        return gateway.getAllAreas();
    }

    @Override
    public Area getById(UUID id) {
        return gateway.getAreaById(id);
    }

    @Override
    public void update(UUID id, UpdateArea dto) {
        gateway.updateArea(id, dto);
    }

    @Override
    public void delete(UUID id) {
        gateway.deleteArea(id);
    }
}
