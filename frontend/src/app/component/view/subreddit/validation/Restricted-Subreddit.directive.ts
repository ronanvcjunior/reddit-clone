import { Injectable } from "@angular/core";
import { waitForAsync } from "@angular/core/testing";
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from "@angular/forms";
import { ValidatorFn } from "@angular/forms";
import { Observable, of } from "rxjs";
import { catchError, delay, map } from "rxjs/operators";
import { SignupUsernames } from "../../auth/signupUsernames";
import { SubredditModel } from "../model/subreddit.model";
import { SubredditService } from "../shared/subreddit.service";

@Injectable({
    providedIn: 'root'
})
export class ValidationSubreddit {

    nameSubredditTestExists: boolean = true

    listSubreddits: SubredditModel[] = []

    constructor(private service: SubredditService) { }

    nameSubredditUniqueValidator(): AsyncValidatorFn {
        return (control: AbstractControl): Observable<ValidationErrors | null> => {
            return this.nameSubredditExists(control.value).pipe(
                map(exists => (exists ? { nameSubreddiExists: true } : null))
            )
        }
    }

    nameSubredditExists(nameSubreddit: string): Observable<boolean> {
        this.listAllSubreddits()

        return of(nameSubreddit).pipe(
                delay(500),
                map(() => {
                    for (var chave in this.listSubreddits) {
                        // console.log(this.users[chave].username.toString())
                        if (this.listSubreddits[chave].name.toString() == nameSubreddit) {
                            // console.log(true);

                            return true
                        }

                    }
                    // console.log(false);

                    return false
                })
        )
    }

    listAllSubreddits(): void {
        this.service.getAllSubreddits().subscribe(resposta => {
            this.listSubreddits = resposta
            // console.log(this.users);

        })
    }
}
