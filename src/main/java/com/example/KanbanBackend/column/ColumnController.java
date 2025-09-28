package com.example.KanbanBackend.column;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService service;

    public ColumnController(ColumnService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ColumnEntity> createColumn(@PathVariable Long boardId,
                                                      @RequestBody ColumnDto dto) {
        return ResponseEntity.status(201).body(service.createColumn(boardId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColumnEntity> updateColumn(@PathVariable Long id,
                                                    @RequestBody UpdateDto dto) {
        return ResponseEntity.ok(service.updateColumn(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Long id) {
        service.deleteColumn(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Void> reorderColumns(@PathVariable Long boardId,
                                               @RequestBody List<ReorderDto> reorderList) {
        service.reorderColumns(reorderList);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<ColumnEntity>> getColumns(@PathVariable Long boardId) {
        return ResponseEntity.ok(service.getColumnsByBoard(boardId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ColumnEntity> getColumnById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
