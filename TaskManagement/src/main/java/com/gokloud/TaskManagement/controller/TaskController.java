package com.gokloud.TaskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gokloud.TaskManagement.model.CustomUserResponse;
import com.gokloud.TaskManagement.model.Task;
import com.gokloud.TaskManagement.model.User;
import com.gokloud.TaskManagement.repositories.TaskRepository;
import com.gokloud.TaskManagement.repositories.UserRepository;


	@RestController
	@RequestMapping("/")
public class TaskController {
		@Autowired
		TaskRepository taskrepo;
		
		@Autowired
		UserRepository userrepo;
		
		
		//creating a method to create users in the DB
		@PostMapping("/createuser")
		public ResponseEntity<User> createUser(@RequestBody User user){
			return ResponseEntity.ok(userrepo.save(user));
		}
		
		//creating a function to create tasks
		@PostMapping("/create/{userid}")
		public ResponseEntity<Task> createTask(@PathVariable("userid") int userId,@RequestBody Task task){
			
			User user = userrepo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
			
			task.setUser(user);
			Task createdtask = taskrepo.save(task);
			
			createdtask.setUser(user);
			
			
			//saving the task to database
			return ResponseEntity.ok(createdtask);
		}
		
//		//getting a list of all the tasks
//		@GetMapping("/tasks")
//		public List<Task> getAllTasks(){
//			return taskrepo.findAll();
//		}
		
		
		//get user information and the tasks along with it
		@GetMapping("/user/{username}/{password}")
		public ResponseEntity<CustomUserResponse> getUserInfo(@PathVariable("username") String username,@PathVariable("password") String pass){
			User user = userrepo.findByusernameAndPassword(username, pass).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found"));
			
			CustomUserResponse custom = new CustomUserResponse();
			
			custom.setUserId(user.getUserId());
			custom.setUsername(user.getUsername());
			custom.setPassword(user.getPassword());
			custom.setTasks(user.getTasks());
			
			return ResponseEntity.ok(custom);
		}
		
		
		
		
//		//finding a particular task with a given task id
//		@GetMapping("/task/{id}")
//		public Task getTaskById(@PathVariable("id") int num) {
//			return taskrepo.findById(num)
//	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found"));
//		}
		
		
		//finding all the tasks based on the given userId
		@GetMapping("/usertasks/{userId}")
		public List<Task> getTaskByUserId(@PathVariable("userId") int num){
			User user = userrepo.findById(num).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
			return user.getTasks();
			}
		
		
		
		
		//updating a particular task with a given id
		@PutMapping("/update/{userid}/{id}")
		public ResponseEntity<Task> updateTaskInfo(@PathVariable("userid") int userid, @PathVariable("id") int num,@RequestBody Task taskobj){
			
			Task taskupdate = taskrepo.findByUserUserIdAndId(userid,num).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found for User"));
			

			taskupdate.setTaskname(taskobj.getTaskname());
			taskupdate.setDescription(taskobj.getDescription());
			taskupdate.setStatus(taskobj.getStatus());
			
			taskrepo.save(taskupdate);
			
			return ResponseEntity.ok(taskupdate);
		}
		
		
		//Deleting a particular task with a given id
		@DeleteMapping("/delete/{userid}/{id}")
		public ResponseEntity<?> deleteTask(@PathVariable("userid") int userid,@PathVariable("id") int num){
			taskrepo.findByUserUserIdAndId(userid,num).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found"));
			
			taskrepo.deleteById(num);
			return ResponseEntity.ok("Task with ID " + num + " has been deleted successfully");
		}
}

