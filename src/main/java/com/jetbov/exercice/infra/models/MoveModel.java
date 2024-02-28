package com.jetbov.exercice.infra.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "MOVES")
@Entity
public class MoveModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String areaName;
    private String oxEarring;
    private int entryHeight;
    private int outHeight;
    private int allocatedPeriod;
}

