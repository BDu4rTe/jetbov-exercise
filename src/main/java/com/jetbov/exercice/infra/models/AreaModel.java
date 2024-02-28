package com.jetbov.exercice.infra.models;

import com.jetbov.exercice.core.dtos.CreateArea;
import com.jetbov.exercice.core.entities.Area;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Table(name="AREAS")
public class AreaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private float gmd;
    private int maxCapacity;

    public Area toEntity() {
        return new Area(this.name, this.gmd, this.maxCapacity);
    }

    public void fromEntity(@NotNull Area entity) {
        this.name = entity.name();
        this.gmd = entity.gmd();
        this.maxCapacity = entity.maxCapacity();
    }

    public void fromCreateDto(@NotNull CreateArea dto){
       this.name = dto.name();
       this.gmd = dto.gmd();
       this.maxCapacity = dto.maxCapacity();
    }
}
