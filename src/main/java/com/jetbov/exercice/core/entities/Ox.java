package com.jetbov.exercice.core.entities;

import java.util.UUID;

public record Ox(
        UUID id,
        String earring,
        float weight,
        float initialWeight
) {
}
