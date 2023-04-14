package com.webapp.RestApplication.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webapp.RestApplication.exception.UserNotFoundException;
import com.webapp.RestApplication.model.Post;
import com.webapp.RestApplication.model.User;
import com.webapp.RestApplication.repository.PostRepository;
import com.webapp.RestApplication.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPARestController {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserJPARestController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/jpa/users")
	public ResponseEntity<List<User>> getAllUsers () {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<String> addUser (@Valid @RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/jpa/users/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable("id") int id) {
		return ResponseEntity.ok(userRepository.findById(id).orElse(null));
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		User user = userOpt.get();
		
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public ResponseEntity<List<Post>> retrievePostsForUser(@PathVariable("id") int id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		return ResponseEntity.ok(userOpt.get().getPosts());
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser (@PathVariable("id") int id, @Valid @RequestBody Post post) {
		User user = checkIfUserExists(id);
		post.setUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(postRepository.save(post));
	}

	public User checkIfUserExists(int id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User not found");
		}
		return userOpt.get();
	}
	
}
