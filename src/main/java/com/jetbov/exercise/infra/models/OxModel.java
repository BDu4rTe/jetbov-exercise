package com.jetbov.exercise.infra.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jetbov.exercise.core.dtos.CreateOx;
import com.jetbov.exercise.core.entities.Ox;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Table(name = "OXES")
@Entity
public class OxModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String earring;

    @Column(nullable = false)
    private float initialWeight;

    private float weight;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "area_id")
    AreaModel currentArea;

    public Ox toEntity() {
        return new Ox(this.id, this.earring, this.weight, this.initialWeight);
    }

    public void fromCreateDto(CreateOx dto) {
        this.earring = dto.earring();
        this.initialWeight = dto.initialWeight();
        this.weight = dto.initialWeight();
    }
}
