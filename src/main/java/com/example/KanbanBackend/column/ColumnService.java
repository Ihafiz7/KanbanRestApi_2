package com.example.KanbanBackend.column;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {
    private final ColumnRepository repository;

    public ColumnService(ColumnRepository repository) {
        this.repository = repository;
    }

    public ColumnEntity createColumn(Long boardId, ColumnDto dto) {
        ColumnEntity column = new ColumnEntity();
        column.setBoardId(boardId);
        column.setName(dto.getName());
        column.setPosition(dto.getPosition());
        return repository.save(column);
    }


    public ColumnEntity updateColumn(Long id, UpdateDto dto) {
        ColumnEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Column not found: " + id));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getPosition() != null) existing.setPosition(dto.getPosition());

        return repository.save(existing);
    }


    public void deleteColumn(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Column not found: " + id);
        }
        repository.deleteById(id);
    }

    public void reorderColumns(List<ReorderDto> reorderList) {
        for (ReorderDto dto : reorderList) {
            ColumnEntity column = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Column not found: " + dto.getId()));
            column.setPosition(dto.getPosition());
            repository.save(column);
        }
    }

    // Get all columns for a board
    public List<ColumnEntity> getColumnsByBoard(Long boardId) {
        return repository.findByBoardIdOrderByPosition(boardId);
    }

    public ColumnEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Column not found: " + id));
    }
}
