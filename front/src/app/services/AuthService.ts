import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  
    private baseUrl = 'http://localhost:8080/api/v1';
    private currentUserEmailSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');

    constructor(private http: HttpClient) {}

    login(username: string, password: string): Observable<any> {
        return this.http.post<any>(`${this.baseUrl}/login`, { login: username, password });
    }
    
    registerUser(email: string, login: string, password: string): Observable<any> {
        const registerUrl = `${this.baseUrl}/register`; 
      
        const userData = {
          email: email,
          login: login,
          password: password
        };
      
        return this.http.post<any>(registerUrl, userData);
    }

    getPublications(): Observable<any[]> {
        
        return this.http.get<any[]>(`${this.baseUrl}/publications`);
    }

    getAccessiblePublications(userEmail: string): Observable<any[]> {
        const url = `${this.baseUrl}/accessibles-publications/${userEmail}`
        return this.http.get<any[]>(url);
    }

    getIssuedPublications(userEmail: string): Observable<any[]> {
        const url = `${this.baseUrl}/issued-publications/${userEmail}`
        return this.http.get<any[]>(url);
    }
    setCurrentUserEmail(email: string): void {
        this.currentUserEmailSubject.next(email);
    }
    
    getCurrentUserEmail(): Observable<string> {
        return this.currentUserEmailSubject.asObservable();
    }
    updatePublicationStatus(publicationId: string, userEmail: string): Observable<any> {
        const url = `${this.baseUrl}/publications/${publicationId}?userEmail=${userEmail}`;
        return this.http.put(url, null);
    }
    getPublicationsWithCategories(): Observable<any[]> {
        const url = `${this.baseUrl}/books-by-category`;
        return this.http.get<any[]>(url);
    }

    getAccessiblePublicationsByLab(labId: number): Observable<any[]> {
        const url = `${this.baseUrl}/lab/${labId}/accessible-publications`;
        return this.http.get<any[]>(url);
    }
    
    getIssuedPublicationsByLab(labId: number): Observable<any[]> {
        const url = `${this.baseUrl}/lab/${labId}/issued-publications`;
        return this.http.get<any[]>(url);
    }
    
    getLostBooksByLab(labId: number): Observable<any[]> {
        const url = `${this.baseUrl}/lab/${labId}/lost-books`;
        return this.http.get<any[]>(url);
    }
    getPublicationsByAuthorAndYear(author: string, year: number): Observable<any[]> {
        const params = { author: author, year: year.toString() };
        return this.http.get<any[]>(`${this.baseUrl}/search` ,{ params: params });
    }

    getPublicationsByPublisher(publisher: string): Observable<any[]> {
        const params = { publisher: publisher};
        return this.http.get<any[]>(`${this.baseUrl}/by-publisher` ,{ params: params });
        
    }
    getTotalPriceByLabId(labId: number): Observable<any> {
        const params = { labId: labId};
        return this.http.get<any[]>(`${this.baseUrl}/total-price` ,{ params: params });
        
    }
    getPublicationsByCategory(labId: number, selectedCategory: string, enteredPrice : number): Observable<any[]> {
        const url = `${this.baseUrl}/publications-category`; 
        const params = { labId: labId.toString(), category: selectedCategory, enteredPrice: enteredPrice.toString() };
    
        return this.http.get<any[]>(url, { params });
    }
      
      
      

      

}