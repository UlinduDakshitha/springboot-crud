package lk.acpt.demo.repository;
import lk.acpt.demo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
