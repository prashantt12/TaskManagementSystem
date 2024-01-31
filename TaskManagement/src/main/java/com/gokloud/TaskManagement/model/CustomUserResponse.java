package com.gokloud.TaskManagement.model;

import java.util.List;

public class CustomUserResponse {
	private int userId;
	private String username;
	private String password;
	private List<Task> tasks;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public CustomUserResponse(int userId, String username, String password, List<Task> tasks) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.tasks = tasks;
	}
	public CustomUserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
