package com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	public UserDtls findByEmail(String email);
	
	public List<UserDtls> findByRole(String role);
	
	public UserDtls findByResetToken(String token);
	
}
