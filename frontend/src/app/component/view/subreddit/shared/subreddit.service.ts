import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SubredditRequestModel } from '../model/subreddit-Request.model';
import { SubredditResponseModel } from '../model/subreddit-response.model';
import { SubredditModel } from '../model/subreddit.model';
import { SubredditPageModel } from '../model/subredditPage.model';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  baseUrl: String = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    const url = `${this.baseUrl}/api/subreddit`
    return this.http.get<Array<SubredditModel>>(url);
  }

  getAllSubredditsPage(sort: string, page: number, size: number): Observable<SubredditPageModel> {
    const url = `${this.baseUrl}/api/subreddit/page/?sort=${sort}&sort=id,desc&page=${page}&size=${size}`
    // console.log(url)
    return this.http.get<SubredditPageModel>(url);
  }

  postSubreddit(subredditRequest: SubredditRequestModel): Observable<SubredditModel> {
    const url = `${this.baseUrl}/api/subreddit`
    return this.http.post<SubredditModel>(url, subredditRequest)
  }

  getSubredditByName(nameSubreddit: string): Observable<SubredditResponseModel> {
    const url = `${this.baseUrl}/api/subreddit/name/${nameSubreddit}`
    return this.http.get<SubredditResponseModel>(url)
  }

  getSubredditByFirstLetter(letter: string): Observable<SubredditModel[]> {
    const url = `${this.baseUrl}/api/subreddit/letter/${letter}`
    return this.http.get<SubredditModel[]>(url)
  }
}
