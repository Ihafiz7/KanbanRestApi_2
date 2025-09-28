package com.example.KanbanBackend.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project/{projectId}/boards")
@RequiredArgsConstructor

public class BoardController {
    private final BoardService service;

    @PostMapping
    public ResponseEntity<BoardEntity> create(@Valid @RequestBody BoardEntity entity){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }

    @GetMapping
    public ResponseEntity<List<BoardEntity>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoardEntity> update(@PathVariable Long id, @Valid @RequestBody BoardEntity entity ){
        return ResponseEntity.ok(service.updateById(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardEntity> getById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
