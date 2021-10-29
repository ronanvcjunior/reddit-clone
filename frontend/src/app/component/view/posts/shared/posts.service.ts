import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostModel } from '../model/post.model';
import { PostPageModel } from '../model/postPage.model';

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

  getAllPostsPage(sort: string, page: number, size: number): Observable<PostPageModel> {
    const url = `${this.baseUrl}/posts/page/?sort=${sort}&sort=voteCount,desc&sort=postId,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<PostPageModel>(url);
  }

  getPostById(postId: number): Observable<PostModel> {
    const url = `${this.baseUrl}/posts/${postId}`
    return this.http.get<PostModel>(url)
  }
}