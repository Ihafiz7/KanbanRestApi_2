package com.example.KanbanBackend.activity;

import com.example.KanbanBackend.enums.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
//
//    @GetMapping("/project/{projectId}")
//    public List<ActivityDTO> getByProject(@PathVariable Long projectId) {
//        return activityService.getByProject(projectId);
//    }
//
//    @GetMapping("/entity/{entityType}/{entityId}")
//    public List<ActivityDTO> getByEntity(@PathVariable EntityType entityType, @PathVariable Long entityId) {
//        return activityService.getByEntity(entityType, entityId);
//    }
}
