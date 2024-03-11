package com.jetbov.exercise.infra.dtos;

import com.jetbov.exercise.core.dtos.MoveOx;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMoveRequest(@NotBlank UUID areaId, @NotNull int allocatedPeriod) {
    public MoveOx toDomainRecord() {
        return new MoveOx(this.areaId(), this.allocatedPeriod());
    }
}
