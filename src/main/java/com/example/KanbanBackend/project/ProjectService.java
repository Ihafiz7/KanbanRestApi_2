package com.example.KanbanBackend.project;

import com.example.KanbanBackend.projectMember.ProjectMemberDTO;
import com.example.KanbanBackend.projectMember.ProjectMemberEntity;
import com.example.KanbanBackend.user.UserEntity;
import com.example.KanbanBackend.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ProjectDTO createOrUpdate(ProjectDTO dto) {
        ProjectEntity project;

        if (dto.getId() != null) {
            project = projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Project not found with id: " + dto.getId()));

            if (dto.getName() != null) project.setName(dto.getName());
            if (dto.getProjectKey() != null) project.setProjectKey(dto.getProjectKey());
            if (dto.getDescription() != null) project.setDescription(dto.getDescription());
            if (dto.getOwnerId() != null) {
                UserEntity owner = userRepository.findById(dto.getOwnerId())
                        .orElseThrow(() -> new RuntimeException("Owner not found with id: " + dto.getOwnerId()));
                project.setOwner(owner);
            }
            if (dto.getMembers() != null) {
                updateMembers(project, dto.getMembers());
            }

        } else {
            project = ProjectsDtoMappers.toEntity(dto);

            if (dto.getOwnerId() != null) {
                UserEntity owner = userRepository.findById(dto.getOwnerId())
                        .orElseThrow(() -> new RuntimeException("Owner not found with id: " + dto.getOwnerId()));
                project.setOwner(owner);
            }

            if (dto.getMembers() != null) {
                Set<ProjectMemberEntity> members = dto.getMembers().stream()
                        .map(mDto -> {
                            UserEntity user = userRepository.findById(mDto.getUserId())
                                    .orElseThrow(() -> new RuntimeException("User not found with id: " + mDto.getUserId()));
                            ProjectMemberEntity member = new ProjectMemberEntity();
                            member.setProject(project);
                            member.setUser(user);
                            return member;
                        })
                        .collect(Collectors.toSet());
                project.getMembers().addAll(members); // add new members
            }
        }

        ProjectEntity saved = projectRepository.save(project);
        return ProjectsDtoMappers.toDto(saved);
    }

    // Helper method to safely update members without replacing the collection
    private void updateMembers(ProjectEntity project, Set<ProjectMemberDTO> dtoMembers) {
        project.getMembers().removeIf(existing ->
                dtoMembers.stream().noneMatch(m -> m.getUserId().equals(existing.getUser().getId()))
        );
        for (ProjectMemberDTO mDto : dtoMembers) {
            boolean exists = project.getMembers().stream()
                    .anyMatch(e -> e.getUser().getId().equals(mDto.getUserId()));
            if (!exists) {
                UserEntity user = userRepository.findById(mDto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + mDto.getUserId()));
                ProjectMemberEntity member = new ProjectMemberEntity();
                member.setProject(project);
                member.setUser(user);
                project.getMembers().add(member);
            }
        }
    }

    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectsDtoMappers::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(ProjectsDtoMappers::toDto)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }
}
