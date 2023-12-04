import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './app/home-page/home-page.component';
import { LoginComponent } from './app/login/login.component'
import { RegisterComponent } from './app/register/register.component';
import { AdminInterfaceComponent } from './app/admin-interface/admin-interface.component';
import { UserInterfaceComponent } from './app/user-interface/user-interface.component';
import { LabInterfaceComponent } from './app/lab-interface/lab-interface.component';



const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomePageComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'admin-interface', component: AdminInterfaceComponent },
    { path: 'user-interface', component: UserInterfaceComponent },
    { path: 'lab-interface', component: LabInterfaceComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
