package com.example.KanbanBackend.task;

import com.example.KanbanBackend.column.ColumnEntity;
import com.example.KanbanBackend.enums.TaskPriority;
import com.example.KanbanBackend.enums.TaskStatus;
import com.example.KanbanBackend.persistance.BaseEntity;
import com.example.KanbanBackend.project.ProjectEntity;
import com.example.KanbanBackend.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)

public class TaskEntity extends BaseEntity {

    @Column(nullable = false)
        private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    private LocalDateTime dueDate;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "assignee_id")
    private UserEntity assignee;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "report_to_id")
    private UserEntity reportTo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    private ColumnEntity column;
}
