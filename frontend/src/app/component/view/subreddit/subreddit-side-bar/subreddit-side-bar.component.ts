import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SubredditService } from '../shared/subreddit.service';
import { SubredditModel } from '../model/subreddit.model';
import { catchError } from 'rxjs/operators';
import { of as observableOf } from 'rxjs';

@Component({
  selector: 'app-subreddit-side-bar',
  templateUrl: './subreddit-side-bar.component.html',
  styleUrls: ['./subreddit-side-bar.component.css']
})
export class SubredditSideBarComponent implements OnInit {

  subreddits: Array<SubredditModel> = [];

  constructor(private subredditService: SubredditService) { }

  ngOnInit(): void {
    this.getSubreddtisPage()
  }

  getSubreddtisPage() {
    this.subredditService.getAllSubredditsPage('numberOfPosts,desc', 0, 30)
      .subscribe(data => {
        this.subreddits = data.content
        // console.log(this.subreddits);

      })
  }
}