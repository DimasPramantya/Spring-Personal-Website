package dimaspramantya.personalwebsite.service;

import dimaspramantya.personalwebsite.entity.Achievement;
import dimaspramantya.personalwebsite.entity.Skill;
import dimaspramantya.personalwebsite.exception.EmailAlreadyRegisteredException;
import dimaspramantya.personalwebsite.exception.UserNotFoundException;
import dimaspramantya.personalwebsite.repository.AchievementRepository;
import dimaspramantya.personalwebsite.repository.SkillRepository;
import dimaspramantya.personalwebsite.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dimaspramantya.personalwebsite.entity.User;
import dimaspramantya.personalwebsite.entity.UserDetail;
import dimaspramantya.personalwebsite.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private UserDetailRepository userDetailRepository;
	private AchievementRepository achievementRepository;
	private SkillRepository skillRepository;
	
	@Autowired
	public UserServiceImpl(
			UserRepository userRepository,
			UserDetailRepository userDetailRepository,
			AchievementRepository achievementRepository,
			SkillRepository skillRepository
	) {
		this.userRepository = userRepository;
		this.userDetailRepository = userDetailRepository;
		this.achievementRepository = achievementRepository;
		this.skillRepository = skillRepository;
	}
	
	@Override
	public User getUserById(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()){
			throw new UserNotFoundException("User with id - " + userId + " not found!");
		}
		return user.get();
	}
	
	@Override
	public UserDetail getUserDetail() {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		return user.get().getUserDetail();
	}

	@Override
	public void deleteUserById(int userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void updateUserProfile(UserDetail userDetail) {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		userDetail.setId(user.get().getUserDetail().getId());
		userDetailRepository.save(userDetail);
	}

	@Override
	public void saveUserAchievement(Achievement achievement) {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		user.get().addAchievement(achievement);
		achievementRepository.save(achievement);
	}

	@Override
	public List<Achievement> getUserAchievement() {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		return user.get().getAchievements();
	}

	@Override
	public void addUserSkill(Skill skill) {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		user.get().getSkills().add(skill);
		skillRepository.save(skill);
		userRepository.save(user.get());
	}

	@Override
	public Set<Skill> getUserSkill() {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		return user.get().getSkills();
	}

	@Override
	public User saveUser(User user) {
			if(userRepository.findByEmail(user.getEmail()).isPresent()){
				throw new EmailAlreadyRegisteredException("Email has been registered!");
			}
			return userRepository.save(user);
    }
	
	@Override
	public void saveUserDetail(UserDetail userDetail) {
		Optional<User> user = userRepository.findByEmail("igkdimas@gmail.com");
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found!!");
		}
		user.get().setUserDetail(userDetail);
		userDetailRepository.save(userDetail);
	}



}
