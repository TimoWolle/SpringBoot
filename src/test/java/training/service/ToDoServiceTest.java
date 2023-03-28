package training.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.respository.ToDoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    private ToDo toDo1;

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @BeforeEach
    public void setup() {
        toDo1 = new ToDo();
        toDo1.setTitel("ToDo 1");
        toDo1.setDescription("Description for ToDo 1");
        toDo1.setPrio(1);
        toDo1.setDueDate("2022-12-31");
        toDo1.setStatus(ToDoStatus.IN_PROGRESS);
    }

    @Test
    void getToDo() {
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(toDo1));
        assertEquals(toDo1, toDoService.getTodo(any(Long.class)));
    }

    @Test
    void getToDoExeption() {
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> toDoService.getTodo(123123));
    }


}