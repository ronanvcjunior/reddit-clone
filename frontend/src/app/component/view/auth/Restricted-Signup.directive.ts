import { Injectable } from "@angular/core";
import { waitForAsync } from "@angular/core/testing";
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from "@angular/forms";
import { ValidatorFn } from "@angular/forms";
import { Observable, of } from "rxjs";
import { catchError, delay, map } from "rxjs/operators";
import { AuthService } from "./shared/auth.service";
import { SignupUsernames } from "./signupUsernames";

@Injectable({
    providedIn: 'root'
})
export class ValidationSignup {
    check: String = ''

    users: SignupUsernames[] = []

    constructor(private service: AuthService) { }

    listUsers(): void {
        this.service.findAllUsers().subscribe(resposta => {
            this.users = resposta
            // console.log(this.users);
            
        })
    }

    usernameExists(username: string): Observable<boolean> {
        this.listUsers()
        return of(username).pipe(
                delay(500),
                map((username) => {
                    // console.log('username: '+username)
                    for(var chave in this.users){
                        // console.log(this.users[chave].username.toString())
                        if (this.users[chave].username.toString() == username) {
                            // console.log(true);
                            
                            return true
                        }
                        
                    }
                    // console.log(false);
                    
                    return false
                })
        )
    }

    usernameUniqueValidator(): AsyncValidatorFn {
        return (control: AbstractControl): Observable<ValidationErrors | null> => {
            return this.usernameExists(control.value).pipe(
                map(exists => (exists ? { usernameExists: true } : null))
            )
        }
    }

    // usernameUniqueValidator(): ValidatorFn {
    //     return (control: AbstractControl): { [key: string]: boolean } | null => {

    //         let username: String = control.value
    //         if (username == null || username == '' || username == ' ') {
    //             username = 'x'
    //         }

    //         console.log(username);


    //         this.service.checkForUsername(username).subscribe(resposta => {
    //             this.check = resposta
    //         })

    //         console.log(this.check);


    //         if (this.check == "true") {
    //             return { 'UsernameNotAllowed': true };
    //         }

    //         return null;
    //     }
    // }
}

export function checkWhitespace(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {

        let test: String = control.value

        if (test == null) {
            test = ' '
        } else {
            test = test.trim()
        }

        if (test.replace(' ', '') != test) {
            return { 'WhiteNotAllowed': true };
        }

        return null;
    }
}