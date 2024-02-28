package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.dtos.MoveOx;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MoveOxDto(@NotBlank UUID areaId, @NotNull int days_amount) {
    public MoveOx toDomainRecord() {
        return new MoveOx(this.areaId(), this.days_amount());
    }
}
