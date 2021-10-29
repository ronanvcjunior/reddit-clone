import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubredditModel } from '../model/subreddit.model';
import { SubredditPageModel } from '../model/subredditPage.model';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  baseUrl: String = 'http://localhost:8080/api'

  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    const url = `${this.baseUrl}/subreddit`
    return this.http.get<Array<SubredditModel>>(url);
  }

  getAllSubredditsPage(sort: string, page: number, size: number): Observable<SubredditPageModel> {
    const url = `${this.baseUrl}/subreddit/page/?sort=${sort}&sort=id,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<SubredditPageModel>(url);
  }
}
