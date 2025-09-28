package com.example.KanbanBackend.project;

import com.example.KanbanBackend.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    boolean existsByProjectKey(String projectKey);

    // List projects where user is a member
    @Query("SELECT p FROM ProjectEntity p JOIN p.members m WHERE m.user = :user")
    List<ProjectEntity> findAllByMember(UserEntity user);
}
