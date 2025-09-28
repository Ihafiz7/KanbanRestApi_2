package com.example.KanbanBackend.activity;

import com.example.KanbanBackend.enums.Action;
import com.example.KanbanBackend.enums.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository repo;

    public void log(Long projectId, Long actorId, EntityType type, Long entityId, Action action, String metadata) {
        ActivityEntity a = ActivityEntity.builder()
                .projectId(projectId)
                .actorId(actorId)
                .entityType(type)
                .entityId(entityId)
                .action(action)
                .metadata(metadata)
                .build();
        repo.save(a);
    }
//
//    public List<ActivityDTO> getByProject(Long projectId) {
//        return repo.findByProjectIdOrderByCreatedAtDesc(projectId).stream().map(this::toDTO).toList();
//    }
//
//    public List<ActivityDTO> getByEntity(EntityType type, Long entityId) {
//        return repo.findByEntityTypeAndEntityIdOrderByCreatedAtAsc(type, entityId).stream().map(this::toDTO).toList();
//    }

    private ActivityDTO toDTO(ActivityEntity e) {
        return ActivityDTO.builder()
                .id(e.getId())
                .projectId(e.getProjectId())
                .actorId(e.getActorId())
                .entityType(e.getEntityType())
                .entityId(e.getEntityId())
                .action(e.getAction())
                .metadata(e.getMetadata())
                .createdAt(e.getCreated_at())
                .build();
    }
}
