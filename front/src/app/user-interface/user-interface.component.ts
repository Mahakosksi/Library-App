import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/AuthService';

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent implements OnInit {

   
  publications: any[] = [];
  accessiblePublications : any[] = [];
  issuedPublications : any[] = [];
  booksCategory : any[] = [];
  searchText: any ='';
  

 

  constructor(private authService: AuthService) { }

  ngOnInit(): void {

  }
  selectedContent: string = ''; 

  changeContent(content: string) {
    this.selectedContent = content;
    if (this.selectedContent === 'publications') {
      this.searchText =''
      this.fetchPublications(); 
    }else{
      if(this.selectedContent === 'access'){
        this.searchText =''
        this.fetchAccessiblePublications();
      }else{
        if(this.selectedContent == 'bookscateg'){
          this.searchText =''

          this.fetchBooksByCategory();

        }else{
          this.searchText =''
          this.fetchIssuedPublication();
        }
       
      }
    }
  }
  fetchBooksByCategory(): void {
    this.authService.getPublicationsWithCategories().subscribe(
      (data) => {
        this.booksCategory = data;
      },
      (error) => {
        console.error('Error fetching publications with categories:', error);
      }
    );
  }
  
  borrowPublication(item: any): void {
    if (item.status === 'On rack') {
      const confirmBorrow = confirm('Do you want to borrow this publication?');
      if (confirmBorrow) {
        this.authService.getCurrentUserEmail().subscribe(
          (currentUser) => {
            if (currentUser) {
              const userEmail = currentUser;
              this.updatePublicationStatus(item.idPub, userEmail);
            }
          },
          (error) => {
            console.error('Error fetching current user:', error);
          }
        );
      }
    } else {
      alert('This publication is not available for borrowing.');
    }
  }
  
  
  updatePublicationStatus(publicationId: any, userEmail: any): void {
    this.authService.updatePublicationStatus(publicationId, userEmail).subscribe(
      (data) => {
        
      },
      (error) => {
        console.error('Error updating publication status:', error);
      }
    );
  }
  
  fetchPublications(): void {
    this.authService.getPublications().subscribe(
      (data) => {
        this.publications = data; 
      },
      (error) => {
        console.error('Error fetching publications:', error);
      }
    );
  }
  fetchAccessiblePublications(): void {
    this.authService.getCurrentUserEmail().subscribe(
      (currentUser) => {
        if (currentUser) {
          const userEmail = currentUser; 
          this.authService.getAccessiblePublications(userEmail).subscribe(
            (data) => {
              console.log("Accessible Publications:", data);
              this.accessiblePublications = data; 
            },
            (error) => {
              console.error('Error fetching accessible publications:', error);
            }
          );
        }
      },
      (error) => {
        console.error('Error fetching current user:', error);
      }
    );
  }
  fetchIssuedPublication(){

    this.authService.getCurrentUserEmail().subscribe(
      (currentUser) => {
        
        if (currentUser) {
          const userEmail = currentUser; 
          this.authService.getIssuedPublications(userEmail).subscribe(
            (data) => {
              
              this.issuedPublications = data; 
            },
            (error) => {
              console.error('Error fetching issued publications:', error);
            }
          );
        }
      },
      (error) => {
        console.error('Error fetching current user:', error);
      }
    );

  }
  

}
