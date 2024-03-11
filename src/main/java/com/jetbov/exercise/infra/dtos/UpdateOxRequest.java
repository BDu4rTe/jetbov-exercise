package com.jetbov.exercise.infra.dtos;

import com.jetbov.exercise.core.dtos.UpdateOx;

public record UpdateOxRequest(
        String earring,
        float weight
) {
    public UpdateOx toDomainRecord() {
        return new UpdateOx(this.earring, this.weight);
    }
}
