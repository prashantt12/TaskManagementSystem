package com.gokloud.TaskManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gokloud.TaskManagement.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByusernameAndPassword(String username,String password);
	Optional<User> findByUserIdAndPassword(int userId, String password);
	Optional<User> findByUsername(String username);
}
