package com.webapp.RestApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.RestApplication.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
