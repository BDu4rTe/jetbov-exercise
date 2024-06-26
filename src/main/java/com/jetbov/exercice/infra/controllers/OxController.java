package com.jetbov.exercice.infra.controllers;

import com.jetbov.exercice.core.entities.Ox;
import com.jetbov.exercice.core.services.OxService;
import com.jetbov.exercice.infra.dtos.CreateOxDto;
import com.jetbov.exercice.infra.dtos.MoveOxDto;
import com.jetbov.exercice.infra.dtos.UpdateOxDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/oxes", produces = {"application/json"})
public class OxController {
    @Autowired
    OxService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<Ox>> getAllOxes(
            @Parameter(description = "desired page number")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "number of items per page")
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var pageable = PageRequest.of(page, size);
        var oxes = service.getAll(pageable);

        return oxes.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
                : ResponseEntity.status(HttpStatus.OK).body(oxes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ox> getOxById(@PathVariable(name = "id") UUID id) {
        var ox = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ox);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> createOx(@RequestBody @Valid CreateOxDto dto) {
        service.create(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping(value = "/{id}/move")
    public ResponseEntity<Void> moveOx(@PathVariable(name = "id") UUID id, @RequestBody MoveOxDto dto) {
        service.move(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateOx(@PathVariable(name = "id") UUID id, @RequestBody @Valid UpdateOxDto dto) {
        service.update(id, dto.toDomainRecord());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOx(@PathVariable(name = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
