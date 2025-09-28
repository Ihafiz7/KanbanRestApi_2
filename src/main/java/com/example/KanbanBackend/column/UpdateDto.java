package com.example.KanbanBackend.column;

import lombok.Data;

@Data
public class UpdateDto {
    private Long id;
    private String name;
    private Integer position;
}
