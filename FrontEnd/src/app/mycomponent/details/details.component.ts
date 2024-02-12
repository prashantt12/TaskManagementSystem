import { Component } from '@angular/core';
import { ApplogicService } from '../../applogic.service';
import { CommonModule } from '@angular/common';
import { Taskdet } from '../../taskdet';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [CommonModule, NavbarComponent],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  constructor(private appservice: ApplogicService, private router: Router) { }

  tasks!: any[];

  ngOnInit(): void {
    const userId = sessionStorage.getItem('userId');
    if (userId) {
      this.getTasks(+userId);
    }
  }

  getTasks(userId: number): void {
    this.appservice.getTasksByUserId(userId).subscribe(data => {
      this.tasks = data;
    },
      error => {
        console.log('Error Fetching Tasks: ', error);
      })
  }

  //updating tasks
  updateTask(task: Taskdet) {
    console.log('ok');
    return this.router.navigate(['/updatetask', task.id])
  }

  //deleting tasks
  deleteTask(task: Taskdet) {
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
      console.error('User ID not found in session.');
      return;
    }

    if (!confirm('Are you sure you want to delete this task?')) {
      return;
    }

    this.appservice.deleteTask(+userId, task.id).subscribe(() => {
      window.location.reload();
    }, error => {
      console.error('Error deleting task: ', error);
    });
  }

  //function to redirect to the view task component
  viewTask(taskId: number): void {
    this.router.navigate(['/view-task', taskId]);
  }
}
