package com.mehebbet_spring_project_1.mehebbet_spring_project_1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.UserDtls;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDtls user = userRepository.findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return new CustomUser(user);
	}

}
