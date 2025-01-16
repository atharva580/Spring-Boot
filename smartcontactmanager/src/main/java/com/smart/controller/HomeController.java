package com.smart.controller;

import java.util.logging.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.smart.entities.Contact;


@Controller
public class HomeController {
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String test() {
//		
//		User user1 = new User();
//		user1.setName("Atharva Kardile");
//		user1.setEmail("atharva@dev.io");
//		
//		Contact contact = new Contact();
//		
//		user1.getContacts().add(contact);
//		
//		userRepository.save(user1);
//		
//		return "working";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// handler for home page
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		
		return "home";
	}
	
	// handler for about page
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		
		return "about";
	}
	
	// handler page for sign up page
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	// handler for register user
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model model, HttpSession session) {
		
		try {
			
			if(!agreement) {
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			if (result1.hasErrors()) {
				System.out.println("Error "+result1.toString());
				// This line is because if error has occured and same page will display then the data filled before error should be remain on form.
				model.addAttribute("user",user);
				return "signup";
			}
			
			user.setRole("User_Role");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("Agreement " + agreement);
			System.out.println("User " + user);
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered ", "alert-success"));
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong "+e.getMessage(), "alert-danger"));
			return "signup";
		}
		
		
	}
	
	@GetMapping("/signin")
	public String customLogin(Model model) {
		
		model.addAttribute("title","Login Page");
		return "login";
	}
}
