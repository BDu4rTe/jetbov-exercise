package com.jetbov.exercice.infra.controllers;

import com.jetbov.exercice.application.AreaServiceImpl;
import com.jetbov.exercice.core.entities.Area;
import com.jetbov.exercice.infra.dtos.CreateAreaDto;
import jakarta.validation.Valid;
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
@RequestMapping("areas")
public class AreaController {
    @Autowired
    AreaServiceImpl service;

    public ResponseEntity<Void> createArea(@RequestBody @Valid CreateAreaDto dto){
        service.create(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    public ResponseEntity<Area> getAreaById(@PathVariable(name = "id") UUID id){
       var area = service.getById(id);
       return ResponseEntity.status(HttpStatus.OK).body(area);
    }

    public ResponseEntity<List<Area>> getAllAreas() {
        var areas = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    public ResponseEntity<Void> updateArea(@PathVariable(name = "id") UUID id, @RequestBody Area entity){
        service.update(id, entity);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    public ResponseEntity<Void> deleteArea(@PathVariable(name = "id") UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
