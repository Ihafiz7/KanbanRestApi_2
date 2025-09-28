package com.example.KanbanBackend.project;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectDTO create(@Valid @RequestBody ProjectDTO projectDTO) {
        return service.createOrUpdate(projectDTO);
    }

    @GetMapping
    public List<ProjectDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO getById(@PathVariable Long id) {
        return service.getProjectById(id);
    }

    @PutMapping("/{id}")
    public ProjectDTO update(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO) {
        projectDTO.setId(id);
        return service.createOrUpdate(projectDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
