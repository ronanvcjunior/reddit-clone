import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostModel } from '../model/post.model';
import { PostPageModel } from '../model/postPage.model';
import { PostService } from '../shared/posts.service';

@Component({
  selector: 'app-post-title',
  templateUrl: './post-title.component.html',
  styleUrls: ['./post-title.component.css']
})
export class PostTitleComponent implements OnInit {

  postDelete!: boolean

  posts!: PostPageModel

  posts$: Array<PostModel> = [];

  status: string = 'reactionsCount,desc';

  numberPosts!: number

  @HostListener("window:scroll")
  scrollFunction() {
    let scrollHeight = document.documentElement.scrollHeight - document.documentElement.clientHeight
    let scroll = Math.round(scrollHeight - document.documentElement.scrollTop)
    // console.log(scroll)
    if (scroll == 0) {
      this.numberPosts += this.numberPosts + 20
      if (this.route.snapshot.url[0] !== undefined) {
        // console.log(this.route.snapshot.url[0].path)
        if (this.route.snapshot.url[0].path === 'r') {
          this.getPostsBySubredditPage(this.status, this.route.snapshot.paramMap.get('name_subreddit'))
        }
      } else {
        this.getPostsPage(this.status)
      }
    }
  }

  constructor(private postService: PostService, private route: ActivatedRoute, private router: Router) {
    this.numberPosts = 20
  }

  ngOnInit(): void {
    if (this.route.snapshot.url[0] !== undefined) {
      // console.log(this.route.snapshot.url[0].path)
      if (this.route.snapshot.url[0].path === 'r') {
        this.getPostsBySubredditPage(this.status, this.route.snapshot.paramMap.get('name_subreddit'))
      }
    } else {
      this.getPostsPage(this.status)
    }
  }

  getPostsPage(sort: string) {
    this.postService.getAllPostsPage(sort, 0, this.numberPosts)
      .subscribe(data => {
        this.posts = data
        // console.log(this.posts)
        this.posts$ = this.posts.content
      })
  }

  getPostsBySubredditPage(sort: string, subredditName: string | null) {
    this.postService.getPostsBySubredditPage(subredditName, sort, 0, this.numberPosts)
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

  openPost(name_subreddit: string, id_post: number):void {
    this.router.navigate([`r/${name_subreddit}/post/${id_post}`])
  }
}