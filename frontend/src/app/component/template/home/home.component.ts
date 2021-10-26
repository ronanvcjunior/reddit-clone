import { Component, OnInit } from '@angular/core';
import { PostModel } from '../../view/posts/post-model';
import { PostService } from '../../view/posts/shared/posts.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts$: Array<PostModel> = [];

  constructor(private postService: PostService) {
    this.postService.getAllPosts().subscribe(post => {
      
      this.posts$ = post;
    })
  }

  ngOnInit(): void {
  }

}
