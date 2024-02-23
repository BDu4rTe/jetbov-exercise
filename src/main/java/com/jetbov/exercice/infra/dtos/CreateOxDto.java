package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.entities.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import jakarta.validation.constraints.NotNull;

public record CreateOxDto(
        @NotNull String earring,
        @NotNull double initialHeight
) {
    public CreateOx toEntity() {
        return new CreateOx(this.earring, this.initialHeight);
    }
}
