package dimaspramantya.personalwebsite.repository;

import dimaspramantya.personalwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import dimaspramantya.personalwebsite.entity.UserDetail;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{
}
