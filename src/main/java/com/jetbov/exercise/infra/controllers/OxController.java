package com.jetbov.exercise.infra.controllers;

import com.jetbov.exercise.core.entities.Ox;
import com.jetbov.exercise.core.services.MoveService;
import com.jetbov.exercise.core.services.OxService;
import com.jetbov.exercise.infra.dtos.CreateOxRequest;
import com.jetbov.exercise.infra.dtos.CreateMoveRequest;
import com.jetbov.exercise.infra.dtos.PaginatedResponse;
import com.jetbov.exercise.infra.dtos.UpdateOxRequest;
import com.jetbov.exercise.infra.models.OxModel;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/oxes", produces = {"application/json"})
public class OxController {
    @Autowired
    OxService oxService;

    @Autowired
    MoveService moveService;

    @GetMapping(value = "/")
    public ResponseEntity<PaginatedResponse<OxModel>> getAllOxes(
            @Parameter(description = "desired page number")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "number of items per page")
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var pageable = PageRequest.of(page, size);
        var oxPage = oxService.getAll(pageable);

        var response = PaginatedResponse.fromPage(oxPage);

        return oxPage.getContent().isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
                : ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ox> getOxById(@PathVariable(name = "id") UUID id) {
        var response = oxService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> createOx(@RequestBody @Valid CreateOxRequest dto) {
        oxService.create(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping(value = "/{id}/move")
    public ResponseEntity<Void> moveOx(@PathVariable(name = "id") UUID id, @RequestBody CreateMoveRequest data) {
        moveService.moveOx(id, data);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateOx(@PathVariable(name = "id") UUID id, @RequestBody @Valid UpdateOxRequest dto) {
        oxService.update(id, dto.toDomainRecord());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOx(@PathVariable(name = "id") UUID id) {
        oxService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
