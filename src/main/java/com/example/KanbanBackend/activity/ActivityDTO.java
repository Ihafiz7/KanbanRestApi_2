package com.example.KanbanBackend.activity;

import com.example.KanbanBackend.enums.Action;
import com.example.KanbanBackend.enums.EntityType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ActivityDTO {
    private Long id;
    private Long projectId;
    private Long actorId;
    private EntityType entityType;
    private Long entityId;
    private Action action;
    private String metadata;
    private LocalDateTime createdAt;
}
