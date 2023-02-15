package easytask.easytask.repository;

import easytask.easytask.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


    @Query("select r from Review r" +
            " join fetch r.completeTask" +
            " join fetch r.customerUser" +  //getCompleteTask().getCustomerUser().getName()
            " where r.irumiUser.id = :id")
    List<Review> findByIrumiId(Long id); //페치조인의 대상이 아닌 엔티티에는 where 사용 가능


}
