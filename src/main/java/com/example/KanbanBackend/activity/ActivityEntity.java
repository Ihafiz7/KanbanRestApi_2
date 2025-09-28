package com.example.KanbanBackend.activity;

import com.example.KanbanBackend.enums.Action;
import com.example.KanbanBackend.enums.EntityType;
import com.example.KanbanBackend.persistance.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@Entity
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Builder

public class ActivityEntity extends BaseEntity {
    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private Long actorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EntityType entityType;

    @Column(nullable = false)
    private Long entityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Action action;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String metadata;

//    @Column(nullable = false, updatable = false)
//    private Instant createdAt = Instant.now();
}
