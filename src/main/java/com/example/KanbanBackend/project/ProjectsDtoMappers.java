package com.example.KanbanBackend.project;

import com.example.KanbanBackend.projectMember.ProjectMemberDTO;
import com.example.KanbanBackend.projectMember.ProjectMemberEntity;
import com.example.KanbanBackend.user.UserEntity;
import com.example.KanbanBackend.user.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjectsDtoMappers {
    public static ProjectDTO toDto(ProjectEntity projectEntity) {
        if (projectEntity == null) return null;
        ProjectDTO dto = new ProjectDTO();
        dto.setId(projectEntity.getId());
        dto.setName(projectEntity.getName());
        dto.setProjectKey(projectEntity.getProjectKey());
        dto.setDescription(projectEntity.getDescription());
        if (projectEntity.getOwner() != null) {
            dto.setOwnerId(projectEntity.getOwner().getId());
        }
        if (projectEntity.getMembers() != null) {
            Set<ProjectMemberDTO> members = projectEntity.getMembers().stream()
                    .map(member -> {
                        ProjectMemberDTO mDto = new ProjectMemberDTO();
                        mDto.setUserId(member.getUser().getId());
                        return mDto;
                    })
                    .collect(Collectors.toSet());
            dto.setMembers(members);
        }

        return dto;
    }

    public static ProjectEntity toEntity(ProjectDTO dto) {
        if (dto == null) return null;

        ProjectEntity entity = new ProjectEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setProjectKey(dto.getProjectKey());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
