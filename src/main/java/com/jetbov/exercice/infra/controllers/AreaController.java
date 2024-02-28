package com.jetbov.exercice.infra.controllers;

import com.jetbov.exercice.application.AreaServiceImpl;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.core.services.AreaService;
import com.jetbov.exercice.infra.dtos.CreateAreaDto;
import com.jetbov.exercice.infra.dtos.UpdateAreaDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("areas")
public class AreaController {
    @Autowired
    AreaService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<Area>> getAllAreas() {
        var areas = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable(name = "id") UUID id) {
        var area = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(area);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> createArea(@RequestBody @Valid CreateAreaDto dto) {
        service.create(dto.toDomainRecord());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateArea(@PathVariable(name = "id") UUID id, @RequestBody UpdateAreaDto dto) {
        service.update(id, dto.toDomainRecord());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable(name = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
