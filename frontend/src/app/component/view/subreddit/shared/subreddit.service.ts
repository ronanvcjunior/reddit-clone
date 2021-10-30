import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubredditRequestModel } from '../model/subreddit-Request.model';
import { SubredditModel } from '../model/subreddit.model';
import { SubredditPageModel } from '../model/subredditPage.model';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  baseUrl: string = 'http://localhost:8080/api/subreddit'

  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    const url = `${this.baseUrl}/subreddit`
    return this.http.get<Array<SubredditModel>>(url);
  }

  getAllSubredditsPage(sort: string, page: number, size: number): Observable<SubredditPageModel> {
    const url = `${this.baseUrl}/page/?sort=${sort}&sort=id,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<SubredditPageModel>(url);
  }

  postSubreddit(subredditRequest: SubredditRequestModel): Observable<SubredditModel> {
    return this.http.post<SubredditModel>(this.baseUrl, subredditRequest)
  }
}
