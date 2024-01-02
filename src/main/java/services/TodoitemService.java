package services;

import models.Todoitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TodoitemRepository;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoitemService {
    @Autowired
    private TodoitemRepository todoitemRepository;

    public Iterable<Todoitem> getAll(){
        return todoitemRepository.findAll();
    }

    public Optional<Todoitem> getById(Long id){
        return todoitemRepository.findById(id);
    }

    public Todoitem save (Todoitem todoitem){
        if (todoitem.getId() == null) {
            todoitem.setCreatedAt(Instant.now());
        }
        todoitem.setUpdatedAt(Instant.now());
        return todoitemRepository.save(todoitem);

    }

    public String delete (Todoitem todoitem){
        todoitemRepository.deleteById(todoitem.getId());
        return "Deleted";
    }



}
