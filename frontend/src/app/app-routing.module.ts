import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/template/home/home.component';
import { CreatePostComponent } from './component/view/posts/create-post/create-post.component';
import { PostPageComponent } from './component/view/posts/post-page/post-page.component';
import { ListSubredditComponent } from './component/view/subreddit/list-subreddit/list-subreddit.component';
import { SubredditPageComponent } from './component/view/subreddit/subreddit-page/subreddit-page.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'submit',
    component: CreatePostComponent
  },
  {
    path: 'r/:name_subreddit',
    component: SubredditPageComponent
  },
  {
    path: 'subreddits/:letter',
    component: ListSubredditComponent
  },
  {
    path: 'r/:name_subreddit/post/:id_post',
    component: PostPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
