package com.webapp.RestApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.RestApplication.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
