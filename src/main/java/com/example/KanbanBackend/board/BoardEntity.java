package com.example.KanbanBackend.board;

import com.example.KanbanBackend.persistance.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class BoardEntity extends BaseEntity {
//    @Column(nullable = false)
//    @Size(min = 4 ,max = 10)
    private String name;

    private Integer position;
//
//    @Column(nullable = false)
    private Long projectId;
}
