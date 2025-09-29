package com.example.KanbanBackend.column;

import com.example.KanbanBackend.task.TaskDTO;
import lombok.Data;

import java.util.List;

@Data
public class ColumnDTO {
    private Long id;
    private Long boardId;
    private String name;
    private Integer position;
    private List<TaskDTO> tasks;
}
