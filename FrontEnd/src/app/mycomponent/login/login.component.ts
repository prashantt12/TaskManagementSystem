import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { error } from 'console';
import { response } from 'express';
import { DetailsComponent } from '../details/details.component';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive, RouterOutlet, NavbarComponent, DetailsComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username!: string;
  password!: string;
  errorMessage!: string;
  isAuthenticated: boolean = false;

  constructor(private httpClient: HttpClient, private router: Router) { };

  private baseURL = 'http://localhost:8080/User';

  onSubmit(): void {
    const user = {
      username: this.username,
      password: this.password
    };

    this.httpClient.post<any>(`${this.baseURL}/login`, user).subscribe(response => {
      if (response.success) {
        sessionStorage.setItem('userId', response.userId)
        this.isAuthenticated = true;
        this.router.navigate(['/details']);
      } else {
        this.errorMessage = 'Invalid Username or Password';
        this.router.navigate(['/login']);
      }
    },
      error => {
        console.log('Error: ', error),
          this.errorMessage = "An error occured. Please try again later.";
      }
    );
  }
}
