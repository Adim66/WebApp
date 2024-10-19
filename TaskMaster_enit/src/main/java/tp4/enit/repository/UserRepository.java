package tp4.enit.repository;

import org.springframework.boot.CommandLineRunner;
import tp4.enit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // For login
    Optional<User> findByEmail(String email);        // For checking existing users during registration

}


