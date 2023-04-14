package com.webapp.RestApplication.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Address {
	@NotNull
	@Size(min=3, max=50)
	private String city;
	
	@NotNull
	@Size(min=2)
	private String state;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Address(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}
}
