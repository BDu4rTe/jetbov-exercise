package com.jetbov.exercice.application;

import com.jetbov.exercice.adapters.OxGateway;
import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.dtos.MoveOx;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.dtos.UpdateOx;
import com.jetbov.exercice.core.services.OxService;

import java.util.List;
import java.util.UUID;

public class OxServiceImpl implements OxService {
    private final OxGateway gateway;

    public OxServiceImpl(OxGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Ox> getAll() {
        return gateway.getAllOxes();
    }

    @Override
    public Ox getById(UUID id) {
        return gateway.getOxById(id);
    }

    @Override
    public void create(CreateOx dto) {
        gateway.createOx(dto);
    }

    @Override
    public void move(UUID oxId, MoveOx data) {

    }

    @Override
    public void update(UUID id, UpdateOx dto) {
        gateway.updateOx(id, dto);
    }

    @Override
    public void delete(UUID id) {
        gateway.deleteOx(id);
    }
}
