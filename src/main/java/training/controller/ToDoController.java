package training.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Around;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.dto.ToDoCreate;
import training.dto.ToDoUpdate;
import training.entity.ToDo;
import training.service.ToDoService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;
    private final ModelMapper mapper;

    HttpHeaders headers = new HttpHeaders();

    {
        headers.add("Wunderbärchen", "Alles zu ihrer zufriedenheit.");
    }

    @PostMapping
    public void insert(@Valid @RequestBody ToDoCreate _toDoCreate){
        this.toDoService.insert(mapper.map(_toDoCreate, ToDo.class));
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
    public ResponseEntity delete(@Valid @PathVariable("id") Long id){
        toDoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<ToDo> getToDo(@PathVariable("id") Long id){

        ToDo todo = toDoService.getTodo(id);

        return new ResponseEntity<>(todo, HttpStatus.OK);

    }
}
