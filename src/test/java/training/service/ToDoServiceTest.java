package training.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.respository.ToDoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    List<ToDo> todoList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        toDo1 = new ToDo();
        toDo1.setTitel("ToDo 1");
        toDo1.setDescription("Description for ToDo 1");
        toDo1.setPrio(1);
        toDo1.setDueDate("2022-12-31");
        toDo1.setStatus(ToDoStatus.IN_PROGRESS);
        todoList.add(toDo1);

        toDo2 = new ToDo();
        toDo2.setTitel("ToDo 2");
        toDo2.setDescription("Description for ToDo 2");
        toDo2.setPrio(2);
        toDo2.setDueDate("2022-12-31");
        toDo2.setStatus(ToDoStatus.IN_PROGRESS);
        todoList.add(toDo2);

        toDo3 = new ToDo();
        toDo3.setTitel("ToDo 3");
        toDo3.setDescription("Description for ToDo 3");
        toDo3.setPrio(3);
        toDo3.setDueDate("2022-12-31");
        toDo3.setStatus(ToDoStatus.COMPLETED);
        todoList.add(toDo3);

    }

    @Test
    void getToDo() {
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(toDo1));
        assertEquals(toDo1, toDoService.getTodo(any(Long.class)));
    }

    @Test
    void getToDoExeption() {
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class ,()->toDoService.getTodo(123123));
    }


}