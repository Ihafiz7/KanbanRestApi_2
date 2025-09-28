package com.example.KanbanBackend.projectMember;

import com.example.KanbanBackend.enums.ProjectAuthority;
import lombok.Data;

@Data
public class ProjectMemberDTO {
    private Long id;
    private Long projectId;
    private Long userId;
}
