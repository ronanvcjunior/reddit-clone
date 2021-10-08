import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../auth.service';
import { SignupRequestPayload } from './signup.request.payload';
import { checkWhitespace } from '../Restricted-Signup.directive';
import { ValidationSignup } from '../Restricted-Signup.directive';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload!: SignupRequestPayload

  signupForm!: FormGroup

  test = 'user'

  users: String[] = []
  
  

  constructor(private formBuilder: FormBuilder, public dialog: MatDialogRef<SignupComponent>, private service: AuthService, private validation: ValidationSignup) {
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
    }, { updateOn: 'blur' })

    // this.service.findAllUsers().subscribe(resposta => {
    //   this.users = resposta
    //   console.log(this.users);
    //   console.log(this.users.includes('test2'));
      
    //   console.log(resposta);
      
    // })
    
  }

  signup() {
    if (!this.signupForm.valid) {
      return;
    }
    this.signupRequestPayload.username = this.signupForm.get('username'.trim())?.value
    this.signupRequestPayload.email = this.signupForm.get('email'.trim())?.value
    this.signupRequestPayload.password = this.signupForm.get('password'.trim())?.value

    this.service.signup(this.signupRequestPayload)
        .subscribe(data => {
          console.log(data)
        })
  }

  closeDialog(): void {
    this.dialog.close()
  }
}
