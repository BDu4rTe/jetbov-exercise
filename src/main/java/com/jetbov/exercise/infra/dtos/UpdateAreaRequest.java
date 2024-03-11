package com.jetbov.exercise.infra.dtos;

import com.jetbov.exercise.core.dtos.UpdateArea;

public record UpdateAreaRequest(
        String name,
        float gmd,
        int maxCapacity
) {
    public UpdateArea toDomainRecord() {
        return new UpdateArea(this.name, this.gmd, this.maxCapacity);
    }
}
