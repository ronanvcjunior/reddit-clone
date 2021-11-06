import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SubredditModel } from '../../subreddit/model/subreddit.model';
import { SubredditService } from '../../subreddit/shared/subreddit.service';
import { PostRequestPayload } from '../model/post.payload';
import { PostService } from '../shared/posts.service';

@Component({
  selector: 'app-create-post-url',
  templateUrl: './create-post-url.component.html',
  styleUrls: ['./create-post-url.component.css']
})
export class CreatePostUrlComponent implements OnInit {

  subreddits$: Array<SubredditModel> = [];

  postRequestPayload!: PostRequestPayload

  createPostForm!: FormGroup

  constructor(private formBuilder: FormBuilder, private postService: PostService, private subredditService: SubredditService) {

    this.postRequestPayload = {
      subredditName: '',
      postName: '',
      url: '',
      description: null
    }
  }

  ngOnInit(): void {
    this.getSubreddit()

    this.createPostForm = this.formBuilder.group({
      subredditName: [null, [Validators.required]],
      postName: [null, [Validators.required]],
      url: [null, [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')]]
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
    this.postRequestPayload.url = this.createPostForm.get('url'.trim())?.value

    if (this.postRequestPayload.url?.search('http://') == -1 || this.postRequestPayload.url?.search('https://') == -1) {
      this.postRequestPayload.url = `http://${this.postRequestPayload.url}`
    }
    console.log(this.postRequestPayload.url)
    

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
