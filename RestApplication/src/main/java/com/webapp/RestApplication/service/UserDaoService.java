package com.webapp.RestApplication.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webapp.RestApplication.exception.UserNotFoundException;
import com.webapp.RestApplication.model.Address;
import com.webapp.RestApplication.model.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int id = 0;
	
//	static {
//		users.add (new User (++ id, "Adam", LocalDateTime.now().minusYears(30), new Address("Delhi", "Delhi")));
//		users.add (new User (++ id, "Eve", LocalDateTime.now().minusYears(29), new Address("","")));
//		users.add (new User (++ id, "", LocalDateTime.now().minusYears(25), new Address(null, null)));
//	}
	
	public ResponseEntity<List<User>> getListOfUsers() {
		return ResponseEntity.ok(users);
	}
	
	public ResponseEntity<String> addUser (User user) {
		user.setId(++ id);
		users.add(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	public ResponseEntity<User> retrieveUser (int idx) {
		User userFound = users.stream().filter(user -> user.getId() == idx).findFirst().orElse(null);
		
		if (null == userFound) throw new UserNotFoundException ("Id = " + idx);
		
//		EntityModel<User> entityModel = EntityModel.of(userFound);
//		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(idx));
		
//		entityModel.add(link.withRel("all-users"));
		
		return ResponseEntity.ok(userFound);
	}
}
