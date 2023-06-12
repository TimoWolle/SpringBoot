package training.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import training.dto.ToDoCreate;
import training.dto.ToDoUpdate;
import training.entity.ToDo;
import training.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TODO_FULLRIGHTS')")
public class ToDoController {
    private final ToDoService toDoService;
    private final ModelMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('TODO_CREATE')")
    public ToDo insert(@Valid @RequestBody ToDoCreate _toDoCreate){
        return toDoService.insert(mapper.map(_toDoCreate, ToDo.class));
    }

    @PutMapping
    @PreAuthorize("hasRole('TODO_UPDATE')")
    public ToDo update(@Valid @RequestBody ToDoUpdate _toDoUpdate){
        ToDo toDo = this.toDoService.getTodo(_toDoUpdate.getId());
        mapper.map(_toDoUpdate, toDo);
        return toDoService.update(toDo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TODO_DELETE')")
    public ResponseEntity delete( @PathVariable("id") Long id){
        toDoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public List<ToDo> getAllTodos() {
        List<ToDo> toDos = toDoService.getAllTodos();
        return toDos;
    }

    //region get completed + count
    @GetMapping("/completed")
    @PreAuthorize("hasRole('TODO_READ')")
    public List<ToDo> getCompletedTodos() {
        List<ToDo> toDos = toDoService.getCompletedTodos();
        return toDos;
    }
    @GetMapping("/completed/count")
    @PreAuthorize("hasRole('TODO_READ')")
    public Long getCountCompletedTodos() {
        return toDoService.getCountCompletedTodos();
    }
    //endregion

    //region get cancelled + count
    @GetMapping("/cancelled")
    @PreAuthorize("hasRole('TODO_READ')")
    public List<ToDo> getCancelledTodos() {
        List<ToDo> toDos = toDoService.getCancelledTodos();
        return toDos;
    }
    @GetMapping("/cancelled/count")
    @PreAuthorize("hasRole('TODO_READ')")
    public Long getCountCancelledTodos() {
        return toDoService.getCountCancelledTodos();
    }
    //endregion

    //region get InProgress + count
    @GetMapping("/inprogress")
    @PreAuthorize("hasRole('TODO_READ')")
    public List<ToDo> getInProgressTodos() {
        List<ToDo> toDos = toDoService.getInProgressTodos();
        return toDos;
    }
    @GetMapping("/inprogress/count")
    @PreAuthorize("hasRole('TODO_READ')")
    public Long getCountInProgressTodos() {
        return toDoService.getCountInProgressTodos();
    }
    //endregion

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<ToDo> getToDo(@PathVariable("id") Long id){

        ToDo todo = toDoService.getTodo(id);

        return new ResponseEntity<>(todo, HttpStatus.OK);

    }
}
