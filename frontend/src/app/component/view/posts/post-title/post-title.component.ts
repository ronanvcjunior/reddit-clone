import { Component, OnInit } from '@angular/core';
import { PostModel } from '../model/post.model';
import { PostService } from '../shared/posts.service';

@Component({
  selector: 'app-post-title',
  templateUrl: './post-title.component.html',
  styleUrls: ['./post-title.component.css']
})
export class PostTitleComponent implements OnInit {

  postDelete!: boolean

  posts$: Array<PostModel> = [];

  status: string = 'postId,desc';

  constructor(private postService: PostService) {
  }

  ngOnInit(): void {
    this.getPostsPage(this.status)
  }

  getPostsPage(sort: string) {
    this.postService.getAllPostsPage(sort, 0, 20)
      .subscribe(data => {
        this.posts$ = data.content
      })
  }

  sortEvent(sort: string) {
    this.status = sort;
    
    this.getPostsPage(sort);
  }

  getPostByUser() {
  }
}