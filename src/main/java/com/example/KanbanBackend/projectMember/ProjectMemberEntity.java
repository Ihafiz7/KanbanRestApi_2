package com.example.KanbanBackend.projectMember;

import com.example.KanbanBackend.enums.ProjectAuthority;
import com.example.KanbanBackend.project.ProjectEntity;
import com.example.KanbanBackend.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "project_member",uniqueConstraints = {@UniqueConstraint(columnNames = {"project_id", "user_id"})})

public class ProjectMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectAuthority authority;



}
