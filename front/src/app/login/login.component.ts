import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/AuthService';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';
  errorMessage: string = '';
  loginMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  
  login(): void {
    if (this.username.trim() !== '' && this.password.trim() !== '') {
      this.authService.login(this.username, this.password)
        .subscribe(
          (response) => {
            this.authService.setCurrentUserEmail(response.email);
            if (response && response.role === 'admin') {
              this.router.navigate(['/admin-interface']); 
            } else {
              if(response && response.role === 'lab'){
                this.router.navigate(['/lab-interface']);
              }else{
                this.router.navigate(['/user-interface']); 

              }
             
            }
          },
          (error) => {
            this.errorMessage = 'Invalid credentials. Please try again.';
          }
        );
    } else {
      this.errorMessage = 'Username and password are required.';
    }
  }
  
  ngOnInit(): void {
   
  }

}
