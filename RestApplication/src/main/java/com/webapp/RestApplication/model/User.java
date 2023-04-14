package com.webapp.RestApplication.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


//@JsonFilter("UserFilter")
@Entity(name="user_details")
public class User {
	@NotBlank
	@Size(min=2)
	@JsonProperty("User Name")
	private String name;
	
//	@JsonIgnore
	@Id
	@JsonProperty("User Id")
	@GeneratedValue
	private int id;
	
	@Past
//	@JsonProperty("DOB")
	private LocalDateTime dob;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	@Valid
	@NotNull
//	private Address address;
//
//	public User(int id, String name, LocalDateTime time, Address address) {
//		super();
//		this.name = name;
//		this.id = id;
//		this.time = time;
//		this.address = address;
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getTime() {
		return dob;
	}
	public void setTime(LocalDateTime time) {
		this.dob = time;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", time=" + dob + "]";
	}

//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

	public User(@NotBlank @Size(min = 2) String name, int id, @Past LocalDateTime time) {
		super();
		this.name = name;
		this.id = id;
		this.dob = time;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
