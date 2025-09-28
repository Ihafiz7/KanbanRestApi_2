package com.example.KanbanBackend.task;

import com.example.KanbanBackend.enums.TaskPriority;
import com.example.KanbanBackend.enums.TaskStatus;
import com.example.KanbanBackend.project.ProjectDTO;
import com.example.KanbanBackend.user.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;

    private UserDTO assignee;
    private UserDTO reportTo;
    private ProjectDTO project;

}
