import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../auth/shared/auth.service';
import { SignupComponent } from '../../auth/signup/signup.component';
import { SubredditResponseModel } from '../model/subreddit-response.model';
import { SubredditService } from '../shared/subreddit.service';

@Component({
  selector: 'app-subreddit-page',
  templateUrl: './subreddit-page.component.html',
  styleUrls: ['./subreddit-page.component.css']
})
export class SubredditPageComponent implements OnInit {

  nameSubreddit!: string

  dateCreate!: string

  subreddit!: SubredditResponseModel;

  constructor(private route: ActivatedRoute, private subredditService: SubredditService, private authService: AuthService, private router: Router, private dialog: MatDialog) {
    this.subreddit = {
      id: 0,
      name: '',
      description: '',
      numberOfPosts: 0,
      userName: '',
      createdDate: 0
    }
   }

  ngOnInit(): void {
    this.nameSubreddit = this.route.snapshot.paramMap.get('name_subreddit')!
    this.getSubreddit()
  }

  getSubreddit() {
    this.subredditService.getSubredditByName(this.nameSubreddit).subscribe(resposta => {
      this.subreddit = resposta
      // console.log(this.subreddit)
      
      let data = new Date(this.subreddit.createdDate * 1000)
      this.dateCreate = ((data.getDate())) + "/" + ((data.getMonth() + 1)) + "/" + data.getFullYear();
      // console.log(this.dateCreate)
    })
  }

  criarPostagem() {
    if (this.authService.getUserName() != null) {
      this.router.navigate(['submit'])
    } else {
      this.openSignup()
    }
  }

  openSignup(): void {
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialog.open(SignupComponent, DIALOG_CONFIG)
  }
}
