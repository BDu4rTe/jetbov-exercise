package com.jetbov.exercise.infra.models;

import com.jetbov.exercise.core.entities.Move;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
//@Builder
@Table(name = "MOVES")
@Entity
public class MoveModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String areaName;

    @Column(nullable = false)
    private String oxEarring;

    @Column(nullable = false)
    private float entryWeight;

    @Column(nullable = false)
    private float outWeight;

    @Column(nullable = false)
    private int allocatedPeriod;

    public Move toEntity() {
        return new Move(
                this.id,
                this.areaName,
                this.oxEarring,
                this.entryWeight,
                this.outWeight,
                this.allocatedPeriod
        );
    }
}

