package easytask.easytask.repository;

import easytask.easytask.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);


    Optional<User> findOneByEmail(String email);
    boolean existsByEmail(String email);

}
