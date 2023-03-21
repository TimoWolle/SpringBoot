package training.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training.entity.ToDo;
import training.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping
    public ToDo insert(@RequestBody ToDo _todo){
        return toDoService.insert(_todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        toDoService.delete(id);
    }

    @GetMapping
    public List<ToDo> getAllTodos() {
        List<ToDo> toDos = toDoService.getAllTodos();
        return toDos;
    }

    //region get completed + count
    @GetMapping("/completed")
    public List<ToDo> getCompletedTodos() {
        List<ToDo> toDos = toDoService.getCompletedTodos();
        return toDos;
    }
    @GetMapping("/completed/count")
    public Long getCountCompletedTodos() {
        return toDoService.getCountCompletedTodos();
    }
    //endregion

    //region get cancelled + count
    @GetMapping("/cancelled")
    public List<ToDo> getCancelledTodos() {
        List<ToDo> toDos = toDoService.getCancelledTodos();
        return toDos;
    }
    @GetMapping("/cancelled/count")
    public Long getCountCancelledTodos() {
        return toDoService.getCountCancelledTodos();
    }
    //endregion

    //region get InProgress + count
    @GetMapping("/inprogress")
    public List<ToDo> getInProgressTodos() {
        List<ToDo> toDos = toDoService.getInProgressTodos();
        return toDos;
    }
    @GetMapping("/inprogress/count")
    public Long getCountInProgressTodos() {
        return toDoService.getCountInProgressTodos();
    }
    //endregion

    @GetMapping("/{id}")
    public ToDo getToDo(@PathVariable("id") Long id){
        return toDoService.getTodo(id);
    }
}
