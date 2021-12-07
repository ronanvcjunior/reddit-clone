import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { CommentModel } from '../model/comment.model';
import { CommentPageModel } from '../model/commentPage.model';
import { CommentPayloadModel } from '../model/commentPayload.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  baseUrl: String = 'http://localhost:8080/api'

  constructor(private http: HttpClient, private _snack: MatSnackBar) { }

  postComment(comment: CommentPayloadModel): Observable<CommentModel> {
    const url = `${this.baseUrl}/comments`
    return this.http.post<CommentModel>(url, comment)
  }

  getCommentForPostPage(idPost: number): Observable<CommentPageModel> {
    const url = `${this.baseUrl}/comments/page/by-post/${idPost}/?sort=id,desc`
    return this.http.get<CommentPageModel>(url)
  }
}
