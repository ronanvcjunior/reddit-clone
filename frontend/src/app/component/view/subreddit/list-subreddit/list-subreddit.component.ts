import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SubredditModel } from '../model/subreddit.model';
import { SubredditService } from '../shared/subreddit.service';

@Component({
  selector: 'app-list-subreddit',
  templateUrl: './list-subreddit.component.html',
  styleUrls: ['./list-subreddit.component.css']
})
export class ListSubredditComponent implements OnInit, AfterViewInit {

  letter!: string

  subreddits!: SubredditModel[]

  constructor(private route: ActivatedRoute, private subredditService: SubredditService) { }
  
  ngOnInit(): void {
    this.letter = this.route.snapshot.paramMap.get('letter')!
    // console.log(this.letter)
    this.getSubreddtis()
  }
  
  ngAfterViewInit(): void {
    document.getElementsByClassName('active')[0].removeAttribute('href')
  }

  getSubreddtis() {
    this.subredditService.getSubredditByFirstLetter(this.letter)
      .subscribe(data => {
        this.subreddits = data
        // console.log(this.subreddits);

      })
  }
  
}
