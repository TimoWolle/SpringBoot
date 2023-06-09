package training.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Around;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.respository.ToDoRepository;


import java.util.List;

@Service
@RequiredArgsConstructor

public class ToDoService {
    private final ToDoRepository todoRespository;

    public ToDo insert(ToDo _todo){
        return todoRespository.save(_todo);
    }
    public ToDo update(ToDo _todo){
        ToDo existingToDo = getTodo(_todo.getId());

        existingToDo.setStatus(_todo.getStatus());
        existingToDo.setPrio(_todo.getPrio());
        existingToDo.setDueDate(_todo.getDueDate());

        return todoRespository.save(existingToDo);
    }
    public void delete(long id){
        todoRespository.deleteById(id);
    }

    public ToDo getTodo(long id){
        return todoRespository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
    }
    public List<ToDo> getAllTodos(){
        return todoRespository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<ToDo> getCompletedTodos(){
        return todoRespository.findByStatus(ToDoStatus.COMPLETED);
    }
    public List<ToDo> getCancelledTodos(){
        return todoRespository.findByStatus(ToDoStatus.CANCELLED);
    }
    public List<ToDo> getInProgressTodos(){
        return todoRespository.findByStatus(ToDoStatus.IN_PROGRESS);
    }
    public long getCountCompletedTodos(){
        return  todoRespository.countByStatus(ToDoStatus.COMPLETED);
    }
    public long getCountCancelledTodos(){
        return  todoRespository.countByStatus(ToDoStatus.CANCELLED);
    }
    public long getCountInProgressTodos(){
        return  todoRespository.countByStatus(ToDoStatus.IN_PROGRESS);
    }
}
