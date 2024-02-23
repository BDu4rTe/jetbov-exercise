package com.jetbov.exercice.infra.controllers;

import com.jetbov.exercice.adapters.AreaRepositoryService;
import com.jetbov.exercice.application.AreaServiceImpl;
import com.jetbov.exercice.application.OxServiceImpl;
import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.infra.dtos.CreateOxDto;
import com.jetbov.exercice.infra.dtos.UpdateOxDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("oxes")
public class OxController {
    @Autowired
    OxServiceImpl service;

    public ResponseEntity<Void> createOx(@RequestBody CreateOxDto dto) {
        service.create(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    public ResponseEntity<Ox> getOxById(@PathVariable(name = "id") UUID id) {
        var ox = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ox);
    }
    public ResponseEntity<List<Ox>> getAllOxes() {
        var oxes = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(oxes);
    }
    public ResponseEntity<Void> updateOx(@PathVariable(name = "id") UUID id, @RequestBody UpdateOxDto dto) {
        var domainRecord = dto.toDomainRecord();
        service.update(id, domainRecord);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    public ResponseEntity<Void> deleteOx(@PathVariable(name = "id")UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
