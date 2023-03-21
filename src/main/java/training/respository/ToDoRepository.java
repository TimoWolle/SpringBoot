package training.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {}
