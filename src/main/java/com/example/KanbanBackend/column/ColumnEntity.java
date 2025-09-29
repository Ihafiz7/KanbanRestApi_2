package com.example.KanbanBackend.column;

import com.example.KanbanBackend.board.BoardEntity;
import com.example.KanbanBackend.persistance.BaseEntity;
import com.example.KanbanBackend.project.ProjectEntity;
import com.example.KanbanBackend.task.TaskEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "columns")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ColumnEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @NotNull
    private String name;

    private Integer position;

    @ManyToOne
    private BoardEntity board;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks = new ArrayList<>();
}
