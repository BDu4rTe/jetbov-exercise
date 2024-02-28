package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.entities.Area;
import jakarta.validation.constraints.NotNull;

public record CreateAreaDto(
    @NotNull String name,
    @NotNull float gmd,
    @NotNull int maxCapacity
) {
    public CreateArea toDomainRecord(){
        return new CreateArea(this.name, this.gmd, this.maxCapacity);
    }
}
