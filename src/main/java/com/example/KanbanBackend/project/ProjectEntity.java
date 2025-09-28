package com.example.KanbanBackend.project;

import com.example.KanbanBackend.persistance.BaseEntity;
import com.example.KanbanBackend.projectMember.ProjectMemberEntity;
import com.example.KanbanBackend.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)

public class ProjectEntity extends BaseEntity {
    @Column(name = "project_name", nullable = false)
    @Size(min = 5,max = 50)
    private String name;

    @Column(name = "project_key" , nullable = false)
    private String projectKey;

    @Column(name = "issue_key", unique = true, length = 50)
    private String issueKey;

    @Column(name = "description", columnDefinition = "Text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectMemberEntity> members = new HashSet<>();



    @PrePersist
    public void generateKey() {
        if (this.issueKey == null) {
            String uuidPart = UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();
            this.issueKey = this.projectKey + "-" + uuidPart;
        }
    }
}
