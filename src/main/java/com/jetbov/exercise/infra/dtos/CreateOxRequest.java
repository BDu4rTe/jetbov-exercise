package com.jetbov.exercise.infra.dtos;

import com.jetbov.exercise.core.dtos.CreateOx;
import jakarta.validation.constraints.NotNull;

public record CreateOxRequest(
        @NotNull String earring,
        @NotNull float initialWeight
) {
    public CreateOx toEntity() {
        return new CreateOx(this.earring, this.initialWeight);
    }
}
