package com.jetbov.exercice.core.entities;

import java.util.UUID;

public record Move(
        UUID id,
        String areaName,
        String oxEarring,
        int entryHeight,
        int outHeight,
        int allocatedPeriod
) {
}
