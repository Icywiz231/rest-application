package com.webapp.RestApplication.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.webapp.RestApplication.model.User;
import com.webapp.RestApplication.service.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class UserRestController {
	
	@Autowired
	private UserDaoService userService;
	
	@GetMapping("/users")
	public MappingJacksonValue getAllUsers () {
		List<User> users = userService.getListOfUsers().getBody();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
		SimpleBeanPropertyFilter simpleFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id","time");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", simpleFilter );
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@PostMapping("/users")
	public ResponseEntity<String> addUser (@Valid @RequestBody User user) {
		return userService.addUser(user);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable("id") int id) {
		return userService.retrieveUser(id);
	}
}
