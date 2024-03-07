package com.jetbov.exercice.core.entities;

import java.util.UUID;

public record Area(
        UUID id,
        String name,
        float gmd,
        int maxCapacity
) {
}
