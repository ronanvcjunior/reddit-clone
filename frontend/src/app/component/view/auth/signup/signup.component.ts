import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../shared/auth.service';
import { SignupRequestPayload } from './signup.request.payload';
import { checkWhitespace } from '../Restricted-Signup.directive';
import { ValidationSignup } from '../Restricted-Signup.directive';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload!: SignupRequestPayload

  signupForm!: FormGroup
  
  isDisabled: boolean = true

  constructor(private formBuilder: FormBuilder, public dialog: MatDialogRef<SignupComponent>, public dialogLogin: MatDialog, private service: AuthService, private validation: ValidationSignup) {
    this.signupRequestPayload = {
      username: '',
      email: '',
      password: ''
    }
   }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      username: [null, [Validators.required, checkWhitespace()], [this.validation.usernameUniqueValidator()]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, checkWhitespace()]]
    }, { updateOn: 'blur', asyncValidator: this.validation.usernameUniqueValidator()})

    
  }

  signup() {
    if (this.signupForm.invalid) {
      return;
    }
    this.signupRequestPayload.username = this.signupForm.get('username'.trim())?.value
    this.signupRequestPayload.email = this.signupForm.get('email'.trim())?.value
    this.signupRequestPayload.password = this.signupForm.get('password'.trim())?.value

    this.service.signup(this.signupRequestPayload)
        .subscribe(data => {
          console.log(data)
        })

    this.closeDialog()
  }

  openLogin(): void {
    this.dialog.close()
    
    const DIALOG_CONFIG = new MatDialogConfig()
    DIALOG_CONFIG.disableClose = true
    DIALOG_CONFIG.autoFocus = true
    this.dialogLogin.open(LoginComponent, DIALOG_CONFIG)
  }

  closeDialog(): void {
    this.dialog.close()
  }
}
