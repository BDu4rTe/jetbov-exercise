package com.jetbov.exercice.infra.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
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
    private float entryHeight;

    @Column(nullable = false)
    private float outHeight;

    @Column(nullable = false)
    private int allocatedPeriod;
}

