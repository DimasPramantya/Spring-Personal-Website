package dimaspramantya.personalwebsite.service;

import dimaspramantya.personalwebsite.entity.Achievement;
import dimaspramantya.personalwebsite.entity.Skill;
import dimaspramantya.personalwebsite.entity.User;
import dimaspramantya.personalwebsite.entity.UserDetail;

import java.util.List;
import java.util.Set;

public interface UserService {
	public User saveUser(User user);
	public void saveUserDetail(UserDetail userDetail);
	public User getUserById(int userId);
	public UserDetail getUserDetail();
	public void deleteUserById(int userId);
	public void updateUserProfile(UserDetail userDetail);
	public void saveUserAchievement(Achievement achievement);
	public List<Achievement> getUserAchievement();
	public void addUserSkill(Skill skill);
	public Set<Skill> getUserSkill();
}
