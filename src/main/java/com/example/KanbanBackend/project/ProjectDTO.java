package com.example.KanbanBackend.project;

import com.example.KanbanBackend.projectMember.ProjectMemberDTO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String projectKey;
    private String description;
    private Long ownerId;

    private Set<ProjectMemberDTO> members = new HashSet<>();

}
