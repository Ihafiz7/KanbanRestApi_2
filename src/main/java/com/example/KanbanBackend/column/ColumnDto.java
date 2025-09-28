package com.example.KanbanBackend.column;

import lombok.Data;

@Data
public class ColumnDto {
    private Long id;
    private String name;
    private Integer position;
    private Long boardId;
}
