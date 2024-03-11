package com.jetbov.exercise.infra.dtos;

import com.jetbov.exercise.core.dtos.CreateArea;
import jakarta.validation.constraints.NotNull;

public record CreateAreaRequest(
        @NotNull String name,
        @NotNull float gmd,
        @NotNull int maxCapacity
) {
    public CreateArea toDomainRecord() {
        return new CreateArea(this.name, this.gmd, this.maxCapacity);
    }
}
