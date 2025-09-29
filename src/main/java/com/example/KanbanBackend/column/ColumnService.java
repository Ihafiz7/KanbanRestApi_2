package com.example.KanbanBackend.column;

import com.example.KanbanBackend.task.Mapper;
import com.example.KanbanBackend.task.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final Mapper taskMapper;

    public List<ColumnDTO> getColumnsByBoardId(Long boardId) {
        List<ColumnEntity> columns = columnRepository.findByBoardIdOrderByPositionAsc(boardId);

        return columns.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public void deleteById(Long id){
        this.columnRepository.deleteById(id);
    }

    private ColumnDTO toDTO(ColumnEntity column) {
        ColumnDTO dto = new ColumnDTO();
        dto.setId(column.getId());

        dto.setBoardId(column.getBoard().getId());
        dto.setName(column.getName());
        dto.setPosition(column.getPosition());

        List<TaskDTO> taskDTOs = column.getTasks().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());

        dto.setTasks(taskDTOs);

        return dto;
    }
}
