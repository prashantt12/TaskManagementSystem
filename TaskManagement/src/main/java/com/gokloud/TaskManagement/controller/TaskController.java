package com.gokloud.TaskManagement.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.gokloud.TaskManagement.model.Task;
import com.gokloud.TaskManagement.model.User;
import com.gokloud.TaskManagement.repositories.TaskRepository;
import com.gokloud.TaskManagement.repositories.UserRepository;


	@RestController
	@RequestMapping("/User")
	@CrossOrigin("http://localhost:4200")
public class TaskController {
		@Autowired
		TaskRepository taskrepo;
		
		@Autowired
		UserRepository userrepo;
		
		
		//creating a method to create users in the DB
		@PostMapping("/createuser")
		public ResponseEntity<User> createUser(@RequestBody User user){
			BCryptPasswordEncoder passencoder=	new BCryptPasswordEncoder();
			String pass = passencoder.encode(user.getPassword());
			user.setPassword(pass);
			return ResponseEntity.ok(userrepo.save(user));
		}
		
		//creating a function to create tasks
		@PostMapping("/create/{userid}")
		public ResponseEntity<Task> createTask(@PathVariable("userid") int userId,@RequestBody Task task){
			
			User user = userrepo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
			
			task.setUser(user);
			Task createdtask = taskrepo.save(task);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(createdtask);
		}
		
		//getting a list of all the tasks
		@GetMapping("/tasks")
		public List<Task> getAllTasks(){
			return taskrepo.findAll();
		}
		
		
		
		//finding a particular task with a given task id
		@GetMapping("/task/{id}")
		public Task getTaskById(@PathVariable("id") int num) {
			return taskrepo.findById(num)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found"));
		}
		
		
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
		
		//function to authenticate login
		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody User user)
		{
			Optional<User> authenticatedUser = userrepo.findByUsername(user.getUsername());
			if(authenticatedUser.isPresent()) {
				User userFromDatabase = authenticatedUser.get();
				String hashedpassword = userFromDatabase.getPassword();
				
				//verifying pasword
				BCryptPasswordEncoder passencoder = new BCryptPasswordEncoder();
				if(passencoder.matches(user.getPassword(), hashedpassword)) {
					int userId = userFromDatabase.getUserId();
					return ResponseEntity.ok().body(Map.of("success", true, "userId", userId));
				}
				
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "Invalid credentials"));
		}
}

