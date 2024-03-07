package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.dtos.UpdateOx;

public record UpdateOxDto(
        String earring,
        float weight
) {
    public UpdateOx toDomainRecord() {
        return new UpdateOx(this.earring, this.weight);
    }
}
