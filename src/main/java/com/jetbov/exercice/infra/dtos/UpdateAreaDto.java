package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.dtos.UpdateArea;

public record UpdateAreaDto(
        String name,
        float gmd,
        int maxCapacity
) {
    public UpdateArea toDomainRecord() {
        return new UpdateArea(this.name, this.gmd, this.maxCapacity);
    }
}
