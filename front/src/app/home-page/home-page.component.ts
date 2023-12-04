import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {



  ngOnInit(): void {
  }
  constructor(private router: Router) {}

  goToRegister(): void {
    this.router.navigate(['/register']);
  }

  goToLogin(): void {
    console.log('Navigating to login page...');
    this.router.navigate(['/login']).then((nav) => {
      console.log('Navigation result:', nav); // Log navigation result
    }).catch(err => {
      console.error('Navigation error:', err); // Log navigation error
    });
  }
  

}
