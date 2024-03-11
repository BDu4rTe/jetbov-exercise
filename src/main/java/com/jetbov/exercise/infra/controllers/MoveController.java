package com.jetbov.exercise.infra.controllers;

import com.jetbov.exercise.core.entities.Move;
import com.jetbov.exercise.core.services.MoveService;
import com.jetbov.exercise.infra.dtos.MoveFilter;
import com.jetbov.exercise.infra.dtos.PaginatedResponse;
import com.jetbov.exercise.infra.models.MoveModel;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RequestMapping(value = "/moves", produces = "application/json")
@RestController
public class MoveController {
    @Autowired
    MoveService service;

    @GetMapping(value = "/")
    public ResponseEntity<PaginatedResponse<MoveModel>> getAll(
            @Parameter(description = "desired page number")
            @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "number of items per page")
            @RequestParam(required = false, defaultValue = "10") int size
    ){

        var pageable = PageRequest.of(page, size);
        var movePage = service.getAll(pageable);

        var response = PaginatedResponse.fromPage(movePage);

        return movePage.getContent().isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
                : ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Move>> searchWithFilter(@RequestBody MoveFilter filter){
        var response = service.searchWithFilter(filter);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/by_earrings")
    public ResponseEntity<List<Move>> getByEarrings(@RequestParam List<String> oxenEarrings){
        var response = service.getByEarrings(oxenEarrings);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
