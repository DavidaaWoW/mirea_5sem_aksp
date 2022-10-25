import { Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {  HttpClient } from '@angular/common/http';
import { Contact } from './contact';
import { Observable } from 'rxjs/internal/Observable';

const baseUrl = 'http://localhost:3000/contact';


@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Contact[]> {
    
    return this.http.get<Contact[]>(baseUrl);
  }

  get(id: any): Observable<Contact> {
    return this.http.get<Contact>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, {name: data});
  }

  update(id: any, data: any): Observable<any> {
    console.log(data);
    return this.http.put(`${baseUrl}/${id}`, {name: data});
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
}
