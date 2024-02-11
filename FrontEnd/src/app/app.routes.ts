import { Routes } from '@angular/router';
import { DetailsComponent } from './mycomponent/details/details.component';
import { AddTaskComponent } from './mycomponent/add-task/add-task.component';
import { LoginComponent } from './mycomponent/login/login.component';
import { UpdatetaskComponent } from './mycomponent/updatetask/updatetask.component';
import { ViewTaskComponent } from './mycomponent/view-task/view-task.component';
import { NavbarComponent } from './mycomponent/navbar/navbar.component';

export const routes: Routes = [
    { path: 'details', component: DetailsComponent },
    { path: 'AddTask', component: AddTaskComponent },
    { path: 'login', component: LoginComponent },
    { path: 'updatetask/:id', component: UpdatetaskComponent },
    { path: 'view-task/:id', component: ViewTaskComponent },
    { path: '', redirectTo: 'login', pathMatch: 'full' }
];
