import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Taskdet } from './taskdet';

@Injectable({
  providedIn: 'root'
})
export class ApplogicService {

  constructor(private httpClient: HttpClient) { }

  private baseURL = "http://localhost:8080/User"


  //function to get tasks by userId
  getTasksByUserId(userId: number): Observable<any> {
    return this.httpClient.get<any>(`${this.baseURL}/usertasks/${userId}`);
  }

  //function to create task based on user ID
  createTask(userId: number, task: Taskdet): Observable<Taskdet> {
    return this.httpClient.post<Taskdet>(`${this.baseURL}/create/${userId}`, task);
  }

  //getting tasks by id
  getTaskById(taskId: number): Observable<Taskdet> {
    return this.httpClient.get<Taskdet>(`${this.baseURL}/task/${taskId}`);
  }

  //function to update the task
  updateTask(userId: number, taskId: number, task: Taskdet): Observable<Taskdet> {
    return this.httpClient.put<Taskdet>(`${this.baseURL}/update/${userId}/${taskId}`, task);
  }

  //deleting a task
  deleteTask(userId: number, taskId: number): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/delete/${userId}/${taskId}`, { responseType: 'text' });
  }

}
