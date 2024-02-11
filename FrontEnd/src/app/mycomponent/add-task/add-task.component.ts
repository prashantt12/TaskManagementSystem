import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApplogicService } from '../../applogic.service';
import { Taskdet } from '../../taskdet';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-add-task',
  standalone: true,
  imports: [FormsModule, NavbarComponent],
  templateUrl: './add-task.component.html',
  styleUrl: './add-task.component.css'
})
export class AddTaskComponent {
  task: Taskdet = new Taskdet();

  constructor(private appService: ApplogicService, private router: Router) { }

  onSubmit(): void {
    const userId = sessionStorage.getItem('userId');

    if (userId) {
      this.appService.createTask(+userId, this.task).subscribe(() => {
        console.log('Task Created Successfully.');
        this.router.navigate(['/details']);
      }, error => {
        console.error('Error Creating Task: ', error);
      });
    } else {
      console.error('User ID not found in Session.');
    }
  }

}
