package com.example.KanbanBackend.column;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/columns")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @GetMapping("/board/{boardId}")
    public List<ColumnDTO> getColumnsByBoardId(@PathVariable Long boardId) {
        return columnService.getColumnsByBoardId(boardId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Long id) {
        columnService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
