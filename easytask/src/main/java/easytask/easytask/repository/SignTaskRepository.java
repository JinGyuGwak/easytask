package easytask.easytask.repository;

import easytask.easytask.entity.SignTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignTaskRepository extends JpaRepository<SignTask,Long> {


    @Query("select s from SignTask s join fetch s.customerUser")
    List<SignTask> findAllSignTask();

}
