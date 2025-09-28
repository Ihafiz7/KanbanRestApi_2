package com.example.KanbanBackend.task;

import com.example.KanbanBackend.project.ProjectDTO;
import com.example.KanbanBackend.project.ProjectEntity;
import com.example.KanbanBackend.user.UserDTO;
import com.example.KanbanBackend.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public TaskEntity toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) return null;

        TaskEntity entity = new TaskEntity();
        entity.setTitle(taskDTO.getTitle());
        entity.setDescription(taskDTO.getDescription());
        entity.setPriority(taskDTO.getPriority());
        entity.setStatus(taskDTO.getStatus());
        entity.setDueDate(taskDTO.getDueDate());

        if (taskDTO.getAssignee() != null) {
            UserEntity assignee = new UserEntity();
            assignee.setId(taskDTO.getAssignee().getId());
            assignee.setUserName(taskDTO.getAssignee().getUserName());
            assignee.setProfilePicture(taskDTO.getAssignee().getProfilePicture());
            entity.setAssignee(assignee);
        }

        if (taskDTO.getReportTo() != null) {
            UserEntity reportTo = new UserEntity();
            reportTo.setId(taskDTO.getReportTo().getId());
            reportTo.setUserName(taskDTO.getReportTo().getUserName());
            reportTo.setProfilePicture(taskDTO.getReportTo().getProfilePicture());
            entity.setReportTo(reportTo);
        }

        if (taskDTO.getProject() != null) {
            ProjectEntity project = new ProjectEntity();
            project.setId(taskDTO.getProject().getId());
            project.setProjectKey(taskDTO.getProject().getProjectKey());
            entity.setProject(project);
        }

        return entity;
    }

    public TaskDTO toDTO(TaskEntity task) {
        if (task == null) return null;

        TaskDTO dto = new TaskDTO();
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setStatus(task.getStatus());

        if (task.getAssignee() != null) {
            UserDTO assignee = new UserDTO();
            assignee.setId(task.getAssignee().getId());
            assignee.setUserName(task.getAssignee().getUserName());
            assignee.setProfilePicture(task.getAssignee().getProfilePicture());
            dto.setAssignee(assignee);
        }

        if (task.getReportTo() != null) {
            UserDTO reportTo = new UserDTO();
            reportTo.setId(task.getReportTo().getId());
            reportTo.setUserName(task.getReportTo().getUserName());
            reportTo.setProfilePicture(task.getReportTo().getProfilePicture());
            dto.setReportTo(reportTo);
        }

        if (task.getProject() != null) {
            ProjectDTO project = new ProjectDTO();
            project.setId(task.getProject().getId());
            project.setProjectKey(task.getProject().getProjectKey());
            dto.setProject(project);
        }

        return dto;
    }

}


