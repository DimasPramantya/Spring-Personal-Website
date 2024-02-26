package dimaspramantya.personalwebsite.controller;

import com.cloudinary.Cloudinary;
import dimaspramantya.personalwebsite.entity.Achievement;
import dimaspramantya.personalwebsite.entity.Skill;
import dimaspramantya.personalwebsite.entity.UserDetail;
import dimaspramantya.personalwebsite.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dimaspramantya.personalwebsite.entity.User;
import dimaspramantya.personalwebsite.response.ResponseObj;
import dimaspramantya.personalwebsite.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private UserService userService;
	private FileUploadService fileService;
	
	@Autowired
	public UserRestController(UserService userService, FileUploadService fileService) {
		this.userService = userService;
		this.fileService = fileService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseObj> postUser(@RequestBody User user) {
			user = userService.saveUser(user);
			ResponseObj res = new ResponseObj(HttpStatus.CREATED.value(), "Register Successfull!");
			return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ResponseObj> getUserById(@PathVariable int userId){
		ResponseObj res = new ResponseObj(HttpStatus.OK.value(), "Get User Successfull!");
		res.setAdditionalProperty("User", userService.getUserById(userId));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseObj> deleteUserById(@PathVariable int userId){
		userService.deleteUserById(userId);
		ResponseObj res = new ResponseObj(HttpStatus.OK.value(), "Delete user with id - " + userId +
				" success!!");
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ResponseEntity<ResponseObj> postUserProfile(
			@RequestPart String profileDesc,
			@RequestPart String github,
			@RequestPart String linkedIn,
			@RequestPart("imageFile") MultipartFile imageFile
	){
		Map<?, ?> cloudFile = fileService.uploadUserPicture(imageFile);
		UserDetail userDetail = new UserDetail(
				profileDesc,
				(String) cloudFile.get("url"),
				linkedIn,
				github
		);
		userService.saveUserDetail(userDetail);
		ResponseObj res = new ResponseObj(HttpStatus.CREATED.value(), "Post user profile successfull!");
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PostMapping("/achievement")
	public ResponseEntity<ResponseObj> saveUserAchievement(
			@RequestPart String name,
			@RequestPart String date,
			@RequestPart String description,
			@RequestPart("imageFile") MultipartFile imageFile
	){
		Map<?, ?> cloudFile = fileService.uploadUserAchievement(imageFile);
		Achievement achievement = new Achievement(
				name,
				date,
				description,
				(String) cloudFile.get("url")
		);
		userService.saveUserAchievement(achievement);

		ResponseObj res = new ResponseObj(HttpStatus.CREATED.value(), "Upload user achievement successfull!");
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@GetMapping("/achievement")
	public ResponseEntity<ResponseObj> getUserAchievement(){
		ResponseObj res = new ResponseObj(HttpStatus.OK.value(), "Fetch user profile successfull!");
		res.setAdditionalProperty("achievements:", userService.getUserAchievement());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/skill")
	public ResponseEntity<ResponseObj> postUserSkill(
			@RequestBody Skill skill
			){
		userService.addUserSkill(skill);
		ResponseObj res = new ResponseObj(HttpStatus.CREATED.value(), "Add user's skill success");
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@GetMapping("/skill")
	public ResponseEntity<ResponseObj> getUserSkill(){
		ResponseObj res = new ResponseObj(HttpStatus.OK.value(), "Fetch user's skill success");
		res.setAdditionalProperty("skills", userService.getUserSkill());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}


	@GetMapping("/profile")
	public ResponseEntity<ResponseObj> getUserProfile(){
		UserDetail userDetail = userService.getUserDetail();
		ResponseObj res = new ResponseObj(HttpStatus.OK.value(), "Fetch user profile success!");
		res.setAdditionalProperty("userDetail", userDetail);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping("/profile")
	public ResponseEntity<ResponseObj> updateUserProfile(
			@RequestPart String profileDesc,
			@RequestPart String github,
			@RequestPart String linkedIn,
			@RequestPart("imageFile") MultipartFile imageFile
	){
		Map<?, ?> cloudFile = fileService.uploadUserPicture(imageFile);
		UserDetail userDetail = new UserDetail(
				profileDesc,
				(String) cloudFile.get("url"),
				linkedIn,
				github
		);
		userService.updateUserProfile(userDetail);
		ResponseObj res = new ResponseObj(HttpStatus.CREATED.value(), "Edit user profile success!!");
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
}
