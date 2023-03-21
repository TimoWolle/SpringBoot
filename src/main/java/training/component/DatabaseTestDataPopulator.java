package training.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.respository.ToDoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseTestDataPopulator implements CommandLineRunner {
    private ToDoRepository todoRespository;

    public DatabaseTestDataPopulator(@Autowired ToDoRepository _todoRespository){
        this.todoRespository = _todoRespository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<ToDo> todoList= new ArrayList<>();

        // Objekt 1
        ToDo todo1 = new ToDo();
        todo1.setTitel("Einkaufen");
        todo1.setDescription("Milch, Brot, Eier kaufen");
        todo1.setPrio(1);
        todo1.setDueDate("2023-03-25");
        todo1.setStatus(ToDoStatus.CANCELLED);

        todoList.add(todo1);

        // Objekt 2
        ToDo todo2 = new ToDo();
        todo2.setTitel("Sport machen");
        todo2.setDescription("Ins Fitnessstudio gehen");
        todo2.setPrio(2);
        todo2.setDueDate("2023-03-27");
        todo2.setStatus(ToDoStatus.IN_PROGRESS);

        todoList.add(todo2);

        // Objekt 3
        ToDo todo3 = new ToDo();
        todo3.setTitel("Arzttermin");
        todo3.setDescription("Zum Augenarzt gehen");
        todo3.setPrio(3);
        todo3.setDueDate("2023-04-02");
        todo3.setStatus(ToDoStatus.COMPLETED);

        todoList.add(todo3);

        // Objekt 4
        ToDo todo4 = new ToDo();
        todo4.setTitel("Verkaufen");
        todo4.setDescription("Auto Verkaufen und Reinigen");
        todo4.setPrio(2);
        todo4.setDueDate("2024-03-25");
        todo4.setStatus(ToDoStatus.IN_PROGRESS);

        todoRespository.saveAll(todoList);
        todoRespository.save(todo4);
        todoRespository.delete(todo2);
    }
}
