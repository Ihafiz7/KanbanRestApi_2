package com.example.KanbanBackend.column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    List<ColumnEntity> findByBoardIdOrderByPosition(Long boardId);
}
