import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SubredditModel } from '../../subreddit/model/subreddit.model';
import { SubredditService } from '../../subreddit/shared/subreddit.service';
import { ValidationSubreddit } from '../../subreddit/validation/Restricted-Subreddit.directive';
import { PostRequestPayload } from '../model/post.payload';
import { PostService } from '../shared/posts.service';

@Component({
  selector: 'app-create-post-text',
  templateUrl: './create-post-text.component.html',
  styleUrls: ['./create-post-text.component.css']
})
export class CreatePostTextComponent implements OnInit {

  subreddits$: Array<SubredditModel> = [];

  postRequestPayload!: PostRequestPayload

  createPostForm!: FormGroup

  constructor(private formBuilder: FormBuilder, private postService: PostService, private subredditService: SubredditService) {

    this.postRequestPayload = {
      subredditName: '',
      postName: '',
      url: null,
      description: ''
    }
  }

  ngOnInit(): void {
    this.getSubreddit()
    
    this.createPostForm = this.formBuilder.group({
      subredditName: [null, [Validators.required]],
      postName: [null, [Validators.required]],
      description: [null]
    })
  }

  getSubreddit() {
    this.subredditService.getAllSubreddits().subscribe(resposta => {
      this.subreddits$ = resposta
    })
  }

  createPost(): void {
    if (this.createPostForm.invalid) {
      return;
    }
    this.postRequestPayload.postName = this.createPostForm.get('postName'.trim())?.value
    this.postRequestPayload.subredditName = this.createPostForm.get('subredditName'.trim())?.value
    this.postRequestPayload.description = this.createPostForm.get('description')?.value



    this.postService.postPost(this.postRequestPayload)
      .subscribe(data => {
        console.log(data)
      }, err => {
        // this.service.mensagemWithTime('Ocorreu um erro ao efetuar o seu login! Tente novamente!', 10000)
        // console.log("erro:")
        //console.log(err)
      })
  }

}
