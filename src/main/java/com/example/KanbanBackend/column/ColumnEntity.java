package com.example.KanbanBackend.column;

import com.example.KanbanBackend.persistance.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ColumnEntity extends BaseEntity {
    @NotNull
    private Long boardId;

    @NotNull
    private String name;

    private Integer position;
}
