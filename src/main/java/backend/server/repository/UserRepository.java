package backend.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import backend.server.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
