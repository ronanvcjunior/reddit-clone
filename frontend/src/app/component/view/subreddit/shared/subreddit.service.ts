import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubredditRequestModel } from '../model/subreddit-Request.model';
import { SubredditResponseModel } from '../model/subreddit-response.model';
import { SubredditModel } from '../model/subreddit.model';
import { SubredditPageModel } from '../model/subredditPage.model';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  baseUrl: string = 'http://localhost:8080/api/subreddit'

  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    return this.http.get<Array<SubredditModel>>(this.baseUrl);
  }

  getAllSubredditsPage(sort: string, page: number, size: number): Observable<SubredditPageModel> {
    const url = `${this.baseUrl}/page/?sort=${sort}&sort=id,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<SubredditPageModel>(url);
  }

  postSubreddit(subredditRequest: SubredditRequestModel): Observable<SubredditModel> {
    return this.http.post<SubredditModel>(this.baseUrl, subredditRequest)
  }

  getSubredditByName(nameSubreddit: string): Observable<SubredditResponseModel> {
    const url = `${this.baseUrl}/name/${nameSubreddit}`
    return this.http.get<SubredditResponseModel>(url)
  }
}
