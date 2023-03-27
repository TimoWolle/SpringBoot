package training.respository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import training.entity.ToDo;
import training.entity.ToDoStatus;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository toDoRepository;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;

    // MACHT ROLLBACK NACH JEDEM TEST DESWEGEN NUR 3 TODOS IN DER DATENBANK
    @BeforeEach
    public void setup() {
        toDo1 = new ToDo();
        toDo1.setTitel("ToDo 1");
        toDo1.setDescription("Description for ToDo 1");
        toDo1.setPrio(1);
        toDo1.setDueDate("2022-12-31");
        toDo1.setStatus(ToDoStatus.IN_PROGRESS);
        toDoRepository.save(toDo1);

        toDo2 = new ToDo();
        toDo2.setTitel("ToDo 2");
        toDo2.setDescription("Description for ToDo 2");
        toDo2.setPrio(2);
        toDo2.setDueDate("2022-12-31");
        toDo2.setStatus(ToDoStatus.IN_PROGRESS);
        toDoRepository.save(toDo2);

        toDo3 = new ToDo();
        toDo3.setTitel("ToDo 3");
        toDo3.setDescription("Description for ToDo 3");
        toDo3.setPrio(3);
        toDo3.setDueDate("2022-12-31");
        toDo3.setStatus(ToDoStatus.COMPLETED);
        toDoRepository.save(toDo3);
    }

    @Test
    void findByStatus() {
        assertTrue(toDoRepository.findByStatus(ToDoStatus.IN_PROGRESS).contains(toDo2));
        assertTrue(toDoRepository.findByStatus(ToDoStatus.IN_PROGRESS).contains(toDo1));

        assertFalse(toDoRepository.findByStatus(ToDoStatus.COMPLETED).contains(toDo2));

        assertTrue(toDoRepository.findByStatus(ToDoStatus.COMPLETED).contains(toDo3));
        assertFalse(toDoRepository.findByStatus(ToDoStatus.IN_PROGRESS).contains(toDo3));
    }

    @Test
    void countByStatus() {
        assertEquals(2, toDoRepository.countByStatus(ToDoStatus.IN_PROGRESS));
        assertEquals(1, toDoRepository.countByStatus(ToDoStatus.COMPLETED));
    }
}