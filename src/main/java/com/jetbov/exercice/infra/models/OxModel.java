package com.jetbov.exercice.infra.models;

import com.jetbov.exercice.core.dtos.CreateOx;
import com.jetbov.exercice.core.entities.Ox;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
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
