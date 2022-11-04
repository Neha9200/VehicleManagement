package com.perfios.vehicleManagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.perfios.vehicleManagement.dto.UserRegistrationDto;
import com.perfios.vehicleManagement.model.User;




public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

//	UserDetails loadUserByUsernameandemail(String email, String password);

	
}