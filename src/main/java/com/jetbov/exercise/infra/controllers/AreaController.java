package com.jetbov.exercise.infra.controllers;

import com.jetbov.exercise.core.entities.Area;
import com.jetbov.exercise.core.services.AreaService;
import com.jetbov.exercise.infra.dtos.CreateAreaRequest;
import com.jetbov.exercise.infra.dtos.PaginatedResponse;
import com.jetbov.exercise.infra.dtos.UpdateAreaRequest;
import com.jetbov.exercise.infra.models.AreaModel;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/areas", produces = {"application/json"})
public class AreaController {
    @Autowired
    AreaService areaService;

    @GetMapping(value = "/")
    public ResponseEntity<PaginatedResponse<AreaModel>> getAllAreas(
            @Parameter(description = "desired page number")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "number of items per page")
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var pageable = PageRequest.of(page, size);
        var areaPage = areaService.getAll(pageable);

        var response = PaginatedResponse.fromPage(areaPage);

        return areaPage.getContent().isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
                : ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable(name = "id") UUID id) {
        var response = areaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> createArea(@RequestBody @Valid CreateAreaRequest dto) {
        areaService.create(dto.toDomainRecord());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateArea(@PathVariable(name = "id") UUID id, @RequestBody UpdateAreaRequest dto) {
        areaService.update(id, dto.toDomainRecord());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable(name = "id") UUID id) {
        areaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
