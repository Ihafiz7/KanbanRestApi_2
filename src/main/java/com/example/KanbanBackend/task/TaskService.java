package com.example.KanbanBackend.task;

import com.example.KanbanBackend.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository repository;
    private final Mapper mapper; // injected mapper
    private final UserRepository userRepository;

    public TaskService(TaskRepository repository, Mapper mapper, UserRepository userRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public TaskDTO create(TaskDTO dto) {

        TaskEntity task = mapper.toEntity(dto);

        //  Get logged-in user
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        UserEntity loggedInUser = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
//
//        //  Set assignee and reportTo
//        if (dto.getAssigneeId() != null) {
//            task.setAssignee(userRepository.findById(dto.getAssigneeId())
//                    .orElseThrow(() -> new RuntimeException("Assignee not found")));
//        } else {
//            task.setAssignee(loggedInUser); // default assignee = logged-in user
//        }
//
//        if (dto.getReportToId() != null) {
//            task.setReportTo(userRepository.findById(dto.getReportToId())
//                    .orElseThrow(() -> new RuntimeException("ReportTo user not found")));
//        }

        TaskEntity saved = repository.save(task);

        return mapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskDTO getTask(Long id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return mapper.toDTO(task);
    }

    public TaskDTO update(Long id, TaskDTO dto) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Update fields
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

//        // Update assignee/reportTo if IDs provided
//        if (dto.getAssigneeId() != null) {
//            task.setAssignee(userRepository.findById(dto.getAssigneeId())
//                    .orElseThrow(() -> new RuntimeException("Assignee not found")));
//        }
//        if (dto.getReportToId() != null) {
//            task.setReportTo(userRepository.findById(dto.getReportToId())
//                    .orElseThrow(() -> new RuntimeException("ReportTo user not found")));
//        }

        TaskEntity saved = repository.save(task);
        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        repository.delete(task);
    }
}
