import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApplogicService } from '../../applogic.service';
import { Taskdet } from '../../taskdet';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-view-task',
  standalone: true,
  imports: [CommonModule, NavbarComponent],
  templateUrl: './view-task.component.html',
  styleUrl: './view-task.component.css'
})
export class ViewTaskComponent {
  taskId!: number;
  task!: Taskdet;

  constructor(private route: ActivatedRoute, private appService: ApplogicService) { }

  ngOnInit(): void {
    this.taskId = this.route.snapshot.params['id'];
    this.getTaskDetails(this.taskId);
  }

  getTaskDetails(taskId: number): void {
    const userId = sessionStorage.getItem('userId');

    if (!userId) {
      console.error('User ID not found in session.');
      return;
    }

    this.appService.getTaskById(taskId).subscribe((task: Taskdet) => {
      console.log('Received task:', task);
      this.task = task;
    })
  }

}
