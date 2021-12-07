import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostModel } from '../model/post.model';
import { PostPageModel } from '../model/postPage.model';
import { PostRequestPayload } from '../model/post.payload';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseUrl: String = 'http://localhost:8080/api'

  constructor(private http: HttpClient, private _snack: MatSnackBar) { }

  getAllPosts(): Observable<Array<PostModel>> {
    const url = `${this.baseUrl}/posts`
    return this.http.get<Array<PostModel>>(url);
  }

  getAllPostsPage(sort: string, page: number, size: number): Observable<PostPageModel> {
    const url = `${this.baseUrl}/posts/page/?sort=${sort}&sort=voteCount,desc&sort=postId,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<PostPageModel>(url);
  }

  getPostsBySubredditPage(subredditName: string | null, sort: string, page: number, size: number): Observable<PostPageModel> {
    const url = `${this.baseUrl}/posts/page/by-subreddit/${subredditName}/?sort=${sort}&sort=postId,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<PostPageModel>(url);
  }

  getPostById(postId: number): Observable<PostModel> {
    const url = `${this.baseUrl}/posts/${postId}`
    return this.http.get<PostModel>(url)
  }

  postPost(post: PostRequestPayload): Observable<PostModel> {
    const url = `${this.baseUrl}/posts`
    return this.http.post<PostModel>(url, post)
  }

  deletePost(postId: number): Observable<void> {
    const url = `${this.baseUrl}/posts/${postId}`
    return this.http.delete<void>(url)
  }

  mensagem(str: String): void {
    this._snack.open(`${str}`, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    })
  }

  mensagemWithTime(str: String, duration: number): void {
    this._snack.open(`${str}`, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration: duration
    })
  }
}