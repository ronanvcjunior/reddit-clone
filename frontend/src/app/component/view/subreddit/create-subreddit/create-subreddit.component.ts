import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SubredditRequestModel } from '../model/subreddit-Request.model';
import { SubredditService } from '../shared/subreddit.service';
import { ValidationSubreddit } from '../validation/Restricted-Subreddit.directive';

@Component({
  selector: 'app-create-subreddit',
  templateUrl: './create-subreddit.component.html',
  styleUrls: ['./create-subreddit.component.css']
})
export class CreateSubredditComponent implements OnInit {

  subredditRequestPayload!: SubredditRequestModel

  createSubredditForm!: FormGroup

  constructor(private formBuilder: FormBuilder, public dialog: MatDialogRef<CreateSubredditComponent>, private service: SubredditService,
    private validation: ValidationSubreddit, private router: Router) {
    this.subredditRequestPayload = {
      name: '',
      description: ''
    }
  }

  ngOnInit(): void {
    this.createSubredditForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern('^[a-zA-Z0-9][a-zA-Z0-9À-ü_]+$'), Validators.minLength(3), Validators.maxLength(21)], [this.validation.nameSubredditUniqueValidator()]],
      description: [null, [Validators.required]]
    }, { asyncValidator: this.validation.nameSubredditUniqueValidator() })
  }
  
  createSubreddit(): void {
    if (this.createSubredditForm.invalid) {
      return;
    }
    this.subredditRequestPayload.name = this.createSubredditForm.get('name'.trim())?.value
    this.subredditRequestPayload.description = this.createSubredditForm.get('description'.trim())?.value
    
    
    this.service.postSubreddit(this.subredditRequestPayload)
    .subscribe(data => {
      // console.log(data)
      this.closeDialog()
      this.router.navigate([`r/${data.name}`])
    }, err => {
      // this.service.mensagemWithTime('Ocorreu um erro ao efetuar o seu login! Tente novamente!', 10000)
      // console.log("erro:")
      //console.log(err)
    })
  }

  closeDialog(): void {
    this.dialog.close()
  }
}
