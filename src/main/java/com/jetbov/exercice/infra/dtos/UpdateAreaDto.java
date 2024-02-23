package com.jetbov.exercice.infra.dtos;

import com.jetbov.exercice.core.entities.Area;

public record UpdateAreaDto(
        String name,
        double gmd,
        int maxCapacity
) {
    public Area toEntity() {
        return new Area(this.name, this.gmd, this.maxCapacity);
    }
}
