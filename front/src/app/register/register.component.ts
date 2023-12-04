import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/AuthService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {



  ngOnInit(): void {
  }
  email: string = '';
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  registerMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.authService.registerUser(this.email, this.username, this.password)
      .subscribe((response) => {
        this.errorMessage = ''; 
        this.registerMessage = 'Registration successful!'; 
        this.router.navigate(['/login']);
      
      }, (error) => {
      
        console.error('Registration failed:', error);
        this.registerMessage = ''; 
        this.errorMessage = 'Registration failed. You need to fill all the rquired fields'; 

    });
  }

}
