import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { DetailsComponent } from '../details/details.component';
import { AuthServiceService } from '../../auth-service.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit(): void { }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
