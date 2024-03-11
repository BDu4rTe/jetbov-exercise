package com.jetbov.exercise.infra.models;

import com.jetbov.exercise.core.dtos.CreateArea;
import com.jetbov.exercise.core.entities.Area;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Table(name = "AREAS")
@Entity
public class AreaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private float gmd;

    @Column(nullable = false)
    private int maxCapacity;

    @OneToMany(mappedBy = "currentArea")
    Set<OxModel> oxesOnArea;

    public Area toEntity() {
        return new Area(this.id, this.name, this.gmd, this.maxCapacity);
    }

    public void fromCreateDto(CreateArea dto) {
        this.name = dto.name();
        this.gmd = dto.gmd();
        this.maxCapacity = dto.maxCapacity();
    }
}
