package dimaspramantya.personalwebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private UserDetail userDetail;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	@JsonIgnore
	private List<Achievement> achievements = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name = "user_skills",
		joinColumns = @JoinColumn(name = "skill_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<Skill> skills = new HashSet<>();

	public User() {}

	public User(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public void addAchievement(Achievement achievement){
		this.achievements.add(achievement);
		achievement.setUser(this);
	}
}
