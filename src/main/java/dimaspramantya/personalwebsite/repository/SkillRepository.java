package dimaspramantya.personalwebsite.repository;

import dimaspramantya.personalwebsite.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
