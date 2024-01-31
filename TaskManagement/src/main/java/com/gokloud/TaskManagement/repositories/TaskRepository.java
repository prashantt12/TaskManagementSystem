package com.gokloud.TaskManagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gokloud.TaskManagement.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByUserUserId(int userId);
	Optional<Task> findByUserUserIdAndId(int userId, int id);
}