package dimaspramantya.personalwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dimaspramantya.personalwebsite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}
