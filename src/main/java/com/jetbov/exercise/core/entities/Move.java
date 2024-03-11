package com.jetbov.exercise.core.entities;

import java.util.UUID;

public record Move(
        UUID id,
        String areaName,
        String oxEarring,
        float entryHeight,
        float outHeight,
        int allocatedPeriod
) {
}
