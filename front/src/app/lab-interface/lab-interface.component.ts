import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/AuthService';

@Component({
  selector: 'app-lab-interface',
  templateUrl: './lab-interface.component.html',
  styleUrls: ['./lab-interface.component.css']
})
export class LabInterfaceComponent implements OnInit {
  publications: any[] = [];
  accessiblePublications: any[] = [];
  issuedPublications: any[] = [];
  lostBooks: any[] = [];
  selectedContent: string = '';
  totalPublicationCost: number = 0;


  searchText : any ='';

  authors: string[] = ['John Smith', 'Emily Johnson', 'Michael Brown','David Jones', 'Jessica Davis', 
  'Christopher Wilson', 'Matthew Anderson','Christopher Wilson', 'Linda Martinez', 'Daniel Thomas', 'Robert Rodriguez','Mary White'];

  publishers: string[] = ['Penguin Books', 'Macmillan Publishers', 'Oxford University Press', 'Cambridge University Press', 'Springer Nature','Routledge', 'Palgrave Macmillan', 'Candlewick Press', 'Usborne Publishing','Abrams Books'];
  categories: string[] = ['Fantasy ', 'Science Fiction', 'Mystery', 'Thriller',
  'Historical Fiction', 'Horror','Non-fiction', 'Biography', 'Young Adult', 'Poetry','Dystopian','Adventure','Classics','Crime',];


  years: number[] = [2018, 2019, 2020, 2021, 2022, 2023]; 

  selectedAuthor: string = '';
  selectedCatgeory : string ='';
  selectedPublisher: string = '';
  selectedYear: number = 0;
  enteredPrice : number =0;
  searchResults: any[] = [];


  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  changeContent(content: string): void {
    this.selectedContent = content;
    switch (this.selectedContent) {
      case 'publications':
        this.searchText ='';
        this.fetchPublications();
        break;
      case 'issuedPublications':
        this.searchText ='';
        this.fetchIssuedPublication();
        break;
      case 'lostBooks':
        this.searchText ='';
        this.fetchLostBooks();
        break;
      case 'searchingPublisher':
        this.searchTextPublisher = ''; 
        this.onSearchPublisherClick(); 
        break;
      case 'searchingCategory':
        this.searchTextCategory = ''; 
        this.onSearchCategoryClick(); 
        break;
      default:
        break;
      
    }
  }

searchPerformed: boolean = false;
// Inside your TypeScript component
searchTextPublisher: string = '';
searchTextCategory: string ='';
searchResultsPublisher: any[] = []; 
searchResultsCategory: any[] = []; 


onSearchPublisherClick() {
  if (this.selectedPublisher) {
    this.authService.getPublicationsByPublisher(this.selectedPublisher)
      .subscribe((data: any[]) => {
        this.searchResultsPublisher = data;
      },
      (error) => {
        console.error('Error fetching by publisher:', error);
      });
  }
   
}

onSearchCategoryClick(): void {
  if (this.selectedCatgeory && this.enteredPrice) {
    this.authService.getCurrentUserEmail().subscribe(
    (currentUser) => {
      if (currentUser) {
        const userEmail = currentUser;
        const labId = this.extractLabIdFromEmail(userEmail);
        this.authService.getPublicationsByCategory(labId, this.selectedCatgeory,this.enteredPrice).subscribe(
          (data) => {
            console.log("jjjj", data )
            this.searchResultsCategory = data;
            console.log("kpokopkoik", this.searchResultsCategory)
          },
          (error) => {
            console.error('Error fetching accessible publications:', error);
          }
        );

        }
      });
    };
  
}


onSearchClick(): void {
  if (this.selectedAuthor && this.selectedYear) {
    this.authService.getPublicationsByAuthorAndYear(this.selectedAuthor, this.selectedYear)
      .subscribe((data: any[]) => {
        this.searchResults = data;
        this.searchPerformed = true; 
      },
      (error) => {
        console.error('Error fetching:', error);
      });
  }
}


  fetchPublications(): void {
    this.fetchAccessiblePublications();
   
  }


  fetchAccessiblePublications(): void {
    this.authService.getCurrentUserEmail().subscribe(
      (currentUser) => {
        if (currentUser) {
          const userEmail = currentUser;
          const labId = this.extractLabIdFromEmail(userEmail);
  
          // Fetch accessible publications
          this.authService.getAccessiblePublicationsByLab(labId).subscribe(
            (data) => {
              this.accessiblePublications = data;
            },
            (error) => {
              console.error('Error fetching accessible publications:', error);
            }
          );
  
          // Fetch total publication cost
          this.authService.getTotalPriceByLabId(labId).subscribe(
            (totalPrice) => {
              console.log("total data", totalPrice)
              this.totalPublicationCost = totalPrice;
            },
            (error) => {
              console.error('Error fetching total publication cost:', error);
            }
          );
        }
      },
      (error) => {
        console.error('Error fetching current user:', error);
      }
    );
  }
  

  fetchIssuedPublication(): void {
    this.authService.getCurrentUserEmail().subscribe(
      (currentUser) => {
        if (currentUser) {
          const userEmail = currentUser;
          const labId = this.extractLabIdFromEmail(userEmail);
          this.authService.getIssuedPublicationsByLab(labId).subscribe(
            (data) => {
              this.issuedPublications = data.filter((publication: any) => publication.issuedEmail !== null);
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

  fetchLostBooks(): void {
    this.authService.getCurrentUserEmail().subscribe(
      (currentUser) => {
        if (currentUser) {
          const userEmail = currentUser;
          const labId = this.extractLabIdFromEmail(userEmail);
          this.authService.getLostBooksByLab(labId).subscribe(
            (data) => {
              this.lostBooks = data;
            },
            (error) => {
              console.error('Error fetching lost books:', error);
            }
          );
        }
      },
      (error) => {
        console.error('Error fetching current user:', error);
      }
    );
  }

  private extractLabIdFromEmail(email: string): number {
    const parts = email.split('@');
    if (parts.length === 2 && parts[1] === 'lab.com') {
      const labId = parseInt(parts[0].replace('lab', ''), 10);
      if (!isNaN(labId)) {
        return labId;
      }
    }
    return -1; // Replace this default value if needed or handle error
  }
}
