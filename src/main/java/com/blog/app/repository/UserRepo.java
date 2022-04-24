package com.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
