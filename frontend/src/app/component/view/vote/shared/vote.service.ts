import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VoteModel } from '../model/vote.model';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  baseUrl: String = environment.baseUrl;

  constructor(private http: HttpClient) { }

  postVote(vote: VoteModel): Observable<VoteModel> {
    const url = `${this.baseUrl}/api/votes`
    return this.http.post<VoteModel>(url, vote)
  }

  getVote(postId: number, username: string): Observable<VoteModel> {
    const url = `${this.baseUrl}/api/votes/${postId}/${username}`
    return this.http.get<VoteModel>(url)
  }
}
