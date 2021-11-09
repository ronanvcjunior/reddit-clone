import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../auth/shared/auth.service';
import { SignupComponent } from '../../auth/signup/signup.component';
import { CommentModel } from '../../comment/model/comment.model';
import { CommentPageModel } from '../../comment/model/commentPage.model';
import { CommentPayloadModel } from '../../comment/model/commentPayload.model';
import { CommentService } from '../../comment/shared/comment.service';
import { SubredditResponseModel } from '../../subreddit/model/subreddit-response.model';
import { SubredditService } from '../../subreddit/shared/subreddit.service';
import { PostModel } from '../model/post.model';
import { PostService } from '../shared/posts.service';

@Component({
  selector: 'app-post-page',
  templateUrl: './post-page.component.html',
  styleUrls: ['./post-page.component.css']
})
export class PostPageComponent implements OnInit {

  post!: PostModel

  subreddit!: SubredditResponseModel;

  comments!: CommentPageModel

  comments$!: CommentModel[]
  
  commentRequestPayload!: CommentPayloadModel

  postId!: number

  subredditName!: string

  dateCreate!: string

  isLoggedIn!: boolean;

  createCommentForm!: FormGroup

  constructor(private route: ActivatedRoute, private postService: PostService, private subredditService: SubredditService, private authService: AuthService, private router: Router, private dialog: MatDialog, private formBuilder: FormBuilder, private commentService: CommentService) {
    this.post = {
      postId: 0,
      postName: '',
      url: '',
      description: '',
      voteCount: 0,
      userName: '',
      subredditName: '',
      commentCount: 0,
      duration: ''
    }

    this.subreddit = {
      id: 0,
      name: '',
      description: '',
      numberOfPosts: 0,
      userName: '',
      createdDate: 0
    }

    this.commentRequestPayload = {
      postId: 0,
      text: ''
    }
   }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn()

    this.postId = +this.route.snapshot.paramMap.get('id_post')!
    this.getPost()
    this.subredditName = this.route.snapshot.paramMap.get('name_subreddit')!
    this.getSubreddit()

    this.getComments()

    this.createCommentForm = this.formBuilder.group({
      text: [null, [Validators.required]]
    })
  }

  getPost(): void {
    this.postService.getPostById(this.postId).subscribe(resposta => {
      this.post = resposta
      // console.log(this.post)
    })
  }

  getSubreddit() {
    this.subredditService.getSubredditByName(this.subredditName).subscribe(resposta => {
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

  createComment(): void {
    if (this.createCommentForm.invalid) {
      return;
    }

    this.commentRequestPayload.postId = this.postId
    this.commentRequestPayload.text = this.createCommentForm.get('text'.trim())?.value



    this.commentService.postComment(this.commentRequestPayload)
      .subscribe(data => {
        // console.log(data)
        window.location.reload()
      }, err => {
        // this.service.mensagemWithTime('Ocorreu um erro ao efetuar o seu login! Tente novamente!', 10000)
        // console.log("erro:")
        //console.log(err)
      })
  }

  getComments(): void {
    this.commentService.getCommentForPostPage(this.postId).subscribe(data => {
      this.comments$ = data.content
      // console.log(this.comments$)
    })
  }
}
