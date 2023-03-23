package training.controller;

import jakarta.validation.Valid;
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
    private final ModelMapper mapper;

    @PostMapping
    public ToDo insert(@RequestBody ToDo _todo){
        return toDoService.insert(_todo);
    }

    @PutMapping
    public ToDo update(@Valid @RequestBody ToDoUpdate _toDoUpdate){
        ToDo toDo = this.toDoService.getTodo(_toDoUpdate.getId());
        mapper.map(_toDoUpdate, toDo);


        //Das untere ist eine Property list, da könntest du ganz genau spezifizieren wie man mappt
        //Das brauchst du, falls deine Properties nicht gleich heißen

//        mapper.typeMap(ToDoUpdate.class, ToDo.class)
//                .addMappings(mapper -> {
//                    mapper.map(ToDoUpdate::getPrio, ToDo::setPrio);
//                    mapper.map(ToDoUpdate::getDueDate, ToDo::setDueDate);
//                    mapper.map(ToDoUpdate::getStatus, ToDo::setStatus);
//                });

        return toDoService.update(toDo);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @PathVariable("id") Long id){
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
