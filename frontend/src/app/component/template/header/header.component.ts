import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { LoginComponent } from '../../view/auth/login/login.component';
import { AuthService } from '../../view/auth/shared/auth.service';
import { SignupComponent } from '../../view/auth/signup/signup.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn!: boolean;

  username!: string;

  focus: boolean = false;
  constructor(private dialog: MatDialog, private authService: AuthService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn()
    
    this.username = this.authService.getUserName()
  }

  focusOrBlur(focus: boolean): void {
    this.focus = focus
  }

  openLogin(): void {
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialog.open(LoginComponent, DIALOG_CONFIG)
  }
   
  openSignup(): void {
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialog.open(SignupComponent, DIALOG_CONFIG)
  }
}
