package com.practice.controller;

import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MyController {
	
	//http://localhost:8080/about

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		System.out.println("Inside about handler...");
		// Putting data in model
		model.addAttribute("name","Atharva Kardile"); 
		model.addAttribute("currentDate",new Date().toLocaleString());
		// example
		String nameString = "abc";
		nameString.toUpperCase(); 
		return "about";
		// about.html 
	}
	
	// handling iterations
	
	@GetMapping("/example-loop")
	public String iterateHandler(Model model) {
		
		// Create a list, set collection
		List<String> names = List.of("Ankit","Laxmi","Karan","John");
		model.addAttribute("names",names); 
		return "iterate"; 
	}
	
	// handler for conditional statements
	@GetMapping("conditional")
	public String conditionHandler(Model model) {
		System.out.println("Conditional handler executed");
		model.addAttribute("isActive",true);
		model.addAttribute("gender","M");
		List<Integer> list1 = List.of(25,45,84,75,21,33);
		model.addAttribute("myList",list1);
		
		return "condition";
	}
	
	// handler for including fragments
	@GetMapping("/service")
	public String servicesHandler(Model model) {
		model.addAttribute("title","I like to eat samosa");
		model.addAttribute("subtitle",LocalDateTime.now().toString());
		return "service";
	}
	
	// handler for new about
	@GetMapping("/newabout")
	public String newAbout() {
		return "aboutnew";
	}
	
	// handler for contact
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
}
