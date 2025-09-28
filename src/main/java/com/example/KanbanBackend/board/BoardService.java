package com.example.KanbanBackend.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {
    private final BoardRepository repository;

    public BoardService (BoardRepository repository){
        this.repository = repository;
    }

    public BoardEntity create(BoardEntity entity){
        return repository.save(entity);
    }

    public List<BoardEntity> getAll(){
        return repository.findAll();
    }

    public Optional<BoardEntity> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public BoardEntity updateById(Long id , BoardEntity entity){
        BoardEntity existing = repository.findById(id).orElseThrow(() ->
                new RuntimeException("No Board found by id" + id));

        if(entity.getName() != null){
            existing.setName(entity.getName());
        }
        if (entity.getPosition() != null){
            existing.setPosition(entity.getPosition());
        }
        if (entity.getProjectId() != null){
            existing.setProjectId(entity.getProjectId());
        }

        return repository.save(existing);
    }
}
