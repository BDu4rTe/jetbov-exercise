package com.jetbov.exercice.infra.models;

import com.jetbov.exercice.core.entities.Ox;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "OXES")
public class OxModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String earring;

    @Column(nullable = false)
    private double initialHeight;

    private double height;

    public Ox toEntity() {
        return new Ox(this.earring, this.height, this.initialHeight);
    }

    public void fromEntity(@NotNull Ox entity) {
        this.earring = entity.earring();
        this.height = entity.height();
        this.initialHeight = entity.initialHeight();
    }
}
