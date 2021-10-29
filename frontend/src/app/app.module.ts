import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxWebstorageModule } from 'ngx-webstorage';

import {MatToolbarModule} from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatChipsModule } from '@angular/material/chips';
import {MatSortModule} from '@angular/material/sort'; 

import { HeaderComponent } from './component/template/header/header.component';
import { SignupComponent } from './component/view/auth/signup/signup.component';
import { LoginComponent } from './component/view/auth/login/login.component';

import { TokenInterceptor } from './util/token-interceptor';
import { HomeComponent } from './component/template/home/home.component';
import { SideBarComponent } from './component/template/side-bar/side-bar.component';
import { PostTitleComponent } from './component/view/posts/post-title/post-title.component';
import { VoteButtonComponent } from './component/view/vote/vote-button/vote-button.component';
import { SubredditSideBarComponent } from './component/view/subreddit/subreddit-side-bar/subreddit-side-bar.component';
import { SideFooterComponent } from './component/template/side-footer/side-footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    SideBarComponent,
    PostTitleComponent,
    VoteButtonComponent,
    SubredditSideBarComponent,
    SideFooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatChipsModule,
    MatSortModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
