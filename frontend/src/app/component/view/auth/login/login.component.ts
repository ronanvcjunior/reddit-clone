import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { LoginRequestPayload } from '../login/login.request.payload';
import { SignupComponent } from '../signup/signup.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequestPayload!: LoginRequestPayload

  loginForm!: FormGroup

  isDisabled: boolean = true

  constructor(private formBuilder: FormBuilder, public dialog: MatDialogRef<LoginComponent>, public dialogSignup: MatDialog, private service: AuthService) {
    this.loginRequestPayload = {
      username: '',
      password: ''
    }
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
    })


  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }
    this.loginRequestPayload.username = this.loginForm.get('username'.trim())?.value
    this.loginRequestPayload.password = this.loginForm.get('password'.trim())?.value

    
    this.service.login(this.loginRequestPayload)
      .subscribe(data => {
        console.log(data)

        if (data) {
          this.closeDialog()
        }
      })
  }

  openSignup(): void {
    this.dialog.close()
    
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialogSignup.open(SignupComponent, DIALOG_CONFIG)
  }

  closeDialog(): void {
    this.dialog.close()
  }

}
