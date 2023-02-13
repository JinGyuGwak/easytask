package easytask.easytask.repository;

import easytask.easytask.entity.CompleteTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompleteTaskRepository extends JpaRepository<CompleteTask,Long> {

}
