package com.perfios.vehicleManagement.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.perfios.vehicleManagement.dto.UserRegistrationDto;
import com.perfios.vehicleManagement.model.Role;
import com.perfios.vehicleManagement.model.User;
import com.perfios.vehicleManagement.repository.UserRepository;
import com.perfios.vehicleManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		// System.out.println("hlo"+user.getFirstName());
		return userRepository.save(user);
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

//	@Override
//	public UserDetails loadUserByUsernameandemail(String email,String password) throws UsernameNotFoundException {
//	
//		User user = userRepository.findByEmailAndPassword(email,password);
//		if(user == null ) {
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
//	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);

//	String pass=	user.getPassword();
//	System.out.println("password from db : "+pass);
		if (user == null) {
			return null;
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

//	@Override
//	public UserDetails loadUserByUsernameandemail(String email, String password) throws UsernameNotFoundException{
//		// TODO Auto-generated method stub
//		User user = userRepository.findByEmailAndPassword(email,password);
//		System.out.println(user);
//		if(user==null) {
//			System.out.println("Emil" +user.getEmail() + user.getPassword());
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
//		
//	}
}