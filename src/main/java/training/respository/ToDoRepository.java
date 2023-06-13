package training.respository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.entity.ToDo;
import training.entity.ToDoStatus;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>  {
    List<ToDo> findByStatus(ToDoStatus status);
    Long countByStatus(ToDoStatus status);

}
