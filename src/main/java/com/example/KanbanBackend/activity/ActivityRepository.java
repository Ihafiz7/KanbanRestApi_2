package com.example.KanbanBackend.activity;

import com.example.KanbanBackend.enums.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
//    List<ActivityEntity> findByProjectIdOrderByCreatedAtDesc(Long projectId);
//    List<ActivityEntity> findByEntityTypeAndEntityIdOrderByCreatedAtAsc(EntityType entityType, Long entityId);
//    List<ActivityEntity> findByProjectIdOrderByCreated_atDesc(Long projectId);

}
