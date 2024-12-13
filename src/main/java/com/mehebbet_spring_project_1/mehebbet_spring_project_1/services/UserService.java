package com.mehebbet_spring_project_1.mehebbet_spring_project_1.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.UserDtls;

public interface UserService {
	public UserDtls saveUser(UserDtls user);

	public UserDtls getUserByEmail(String email);

	public List<UserDtls> getUsers(String role);

	public Boolean updateAccountStatus(Integer id, Boolean status);

	public void increaseFailedAttempt(UserDtls user);

	public void userAccountLock(UserDtls user);

	public boolean unlockAccountTimeExpired(UserDtls user);

	public boolean resetAttempt(Integer userId);

	public void updateUserResetToken(String email, String resetToken);
	
	public UserDtls getUserByToken(String token);
	
	public UserDtls updateUser(UserDtls user);
	
	public UserDtls updateUserProfile(UserDtls user, MultipartFile img);
	
	public UserDtls saveAdmin(UserDtls user);
}
