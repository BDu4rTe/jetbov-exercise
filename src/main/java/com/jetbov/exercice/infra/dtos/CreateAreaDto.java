package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.entities.Area;
import jakarta.validation.constraints.NotNull;

public record CreateAreaDto(
    @NotNull String name,
    @NotNull double gmd,
    @NotNull int maxCapacity
) {
    public Area toEntity(){
        return new Area(this.name, this.gmd, this.maxCapacity);
    }
}
