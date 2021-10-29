import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AuthService } from '../../auth/shared/auth.service';
import { SignupComponent } from '../../auth/signup/signup.component';
import { PostModel } from '../../posts/model/post.model';
import { PostService } from '../../posts/shared/posts.service';
import { VoteModel } from '../model/vote.model';
import { VoteService } from '../shared/vote.service';

@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.css']
})
export class VoteButtonComponent implements OnInit {

  vote: VoteModel = {
    voteType: '',
    postId: 0
  }

  voteType: string = ' '
  username: string = ''

  @Input() post!: PostModel;

  constructor(private service: VoteService, private servicePost: PostService, private dialog: MatDialog, private authService: AuthService) { }

  ngOnInit(): void {
    if (this.authService.getUserName() != null) {
      this.username = this.authService.getUserName()
      this.getVote()
    }
  }

  getVote(): void {
    this.service.getVote(this.post.postId, this.username).subscribe(resposta => {
      if (resposta.voteType != null) {
        this.voteType = resposta.voteType
        // console.log(this.voteType)
      } else {
        this.voteType = ''
      }
    }, err => {
      
      this.voteType = ''
    })

    // console.log(this.voteType);
    
  }

  postVote(postId: number, voteType: string): void {
    this.vote.postId = postId
    this.vote.voteType = voteType
    this.service.postVote(this.vote).subscribe(() => {
      this.atualizarPost(postId)
      this.getVote()
    }, err => {
      if (err.error == null || err.error.status != 500 || err.error.message == "Invalid refresh Token") {
        this.openSignup()
      }
      
    })
  }

  atualizarPost(postId: number) {
    this.servicePost.getPostById(postId).subscribe( resposta => {
      this.post = resposta
    })
  }

  openSignup(): void {
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialog.open(SignupComponent, DIALOG_CONFIG)
  }
}
