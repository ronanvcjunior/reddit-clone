import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostModel } from '../post-model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseUrl: String = 'http://localhost:8080/api'

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Array<PostModel>> {
    const url = `${this.baseUrl}/posts`
    return this.http.get<Array<PostModel>>(url);
  }
}