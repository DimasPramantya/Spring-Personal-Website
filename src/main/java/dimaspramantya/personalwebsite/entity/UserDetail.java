package dimaspramantya.personalwebsite.entity;

import org.hibernate.engine.internal.CascadePoint;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_details")
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "profile_description", columnDefinition = "TEXT")
	private String profileDesc;
	
	@Column(name = "profile_picture")
	private String profilePict;
	
	@Column(name = "linkedIn")
	private String linkedIn;
	
	@Column(name = "github")
	private String github;

	public UserDetail() {}
	
	public UserDetail(String profileDesc, String profilePict, String linkedIn, String github) {
		super();
		this.profileDesc = profileDesc;
		this.profilePict = profilePict;
		this.linkedIn = linkedIn;
		this.github = github;
	}
}
