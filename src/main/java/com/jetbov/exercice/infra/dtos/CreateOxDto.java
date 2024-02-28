package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.dtos.CreateOx;
import jakarta.validation.constraints.NotNull;

public record CreateOxDto(
        @NotNull String earring,
        @NotNull float initialHeight
) {
    public CreateOx toEntity() {
        return new CreateOx(this.earring, this.initialHeight);
    }
}
