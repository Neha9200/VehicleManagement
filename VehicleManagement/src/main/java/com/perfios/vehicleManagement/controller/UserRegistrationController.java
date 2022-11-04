package com.perfios.vehicleManagement.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perfios.vehicleManagement.dto.UserRegistrationDto;
import com.perfios.vehicleManagement.model.User;
import com.perfios.vehicleManagement.repository.UserRepository;
import com.perfios.vehicleManagement.service.UserService;

import io.swagger.annotations.Api;

@Controller
@Api(value = "This is spring boot rest api and hibernate vehicle mngnt application")
public class UserRegistrationController {
	private UserService userService;

	// private BCryptPasswordEncoder passwordEncoder;
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
		// this.passwordEncoder=passwordEncoder;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

	@PostMapping("/registersubmit")
	public String registersubmit(UserRegistrationDto user) {
		userService.save(user);

		return "login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/display")
	public String verify(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		UserDetails user = userService.loadUserByUsername(email);

		/*
		 * if (s.toString().equals(s2.toString()) == true) { System.out.println("wow");
		 * } else System.out.println("not wow");
		 */
		if (user != null) {
			Collection<? extends GrantedAuthority> s = user.getAuthorities();
			System.out.println(s + "db");
			Collection<Object> s2 = new ArrayList();
			s2.add("ROLE_USER");
			// s.toString()
			System.out.println(s2.toString());
			System.out.println("ji" + s.toString().equals(s2.toString()));
			System.out.println(s2);
			System.out.println(s);
			System.out.println("idk" + user.getAuthorities().equals(s2));

			if (s.toString().equals(s2.toString()) == true) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				if (encoder.matches(password, user.getPassword())) {
					return "redirect:/vehicle";
				} else {
					session.setAttribute("msg", "Username and password don't match!");
				}
			}
			else {
				return "redirect:/admin";
			}
			
		}
		else {
			session.setAttribute("msg", "Username doesn't exist!");
			return "login";
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
}