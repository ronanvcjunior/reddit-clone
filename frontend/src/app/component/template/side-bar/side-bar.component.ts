import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../../view/auth/shared/auth.service';
import { SignupComponent } from '../../view/auth/signup/signup.component';
import { CreateSubredditComponent } from '../../view/subreddit/create-subreddit/create-subreddit.component';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor(private dialog: MatDialog, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  criarPostagem() {
    if (this.authService.getUserName() != null) {
      this.router.navigate(['submit'])
    } else {
      this.openSignup()
    }
  }

  criarSubreddit() {
    if (this.authService.getUserName() != null) {
      this.openCreateSubreddit()
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

  openCreateSubreddit(): void {
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialog.open(CreateSubredditComponent, DIALOG_CONFIG)
  }
  
}
