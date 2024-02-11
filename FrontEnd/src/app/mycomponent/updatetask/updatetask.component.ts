import { Component, OnInit } from '@angular/core';
import { Taskdet } from '../../taskdet';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplogicService } from '../../applogic.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-updatetask',
  standalone: true,
  imports: [FormsModule, CommonModule, NavbarComponent],
  templateUrl: './updatetask.component.html',
  styleUrl: './updatetask.component.css'
})
export class UpdatetaskComponent implements OnInit {
  taskId!: number;
  taskdet!: Taskdet;

  constructor(private route: ActivatedRoute, private router: Router, private appservice: ApplogicService) { }

  ngOnInit(): void {
    this.taskId = this.route.snapshot.params['id'];
    this.getTaskDetails(this.taskId);
  }

  getTaskDetails(taskId: number): void {
    this.appservice.getTaskById(taskId).subscribe((taskdet: Taskdet) => {
      this.taskdet = taskdet;
    });
  }

  updateTask(): void {
    if (!this.taskdet) {
      console.error('Task is Undefined.');
      return;
    }
    const userIdString: string | null = sessionStorage.getItem('userId');
    if (!userIdString) {
      console.error('User ID is not available.');
      return;
    }

    const userId: number = parseInt(userIdString, 10);
    if (isNaN(userId)) {
      console.error('User ID is not a valid number.');
      return;
    }
    const taskId = this.taskdet.id;

    this.appservice.updateTask(userId, taskId, this.taskdet).subscribe(updatedTask => {
      this.router.navigate(['/details']);
    })
  }
}
