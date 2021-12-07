import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VoteModel } from '../model/vote.model';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  baseUrl: string = 'http://localhost:8080/api/votes'

  constructor(private http: HttpClient) { }

  postVote(vote: VoteModel): Observable<VoteModel> {
    return this.http.post<VoteModel>(this.baseUrl, vote)
  }

  getVote(postId: number, username: string): Observable<VoteModel> {
    const url = `${this.baseUrl}/${postId}/${username}`
    return this.http.get<VoteModel>(url)
  }
}
