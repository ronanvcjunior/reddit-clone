import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SignupRequestPayload } from './signup/signup.request.payload';

import { LoginRequestPayload } from './login/login.request.payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: String = 'http://localhost:8080/api'

  constructor(private http: HttpClient) { }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    const url = `${this.baseUrl}/auth/signup`
    return this.http.post(url, signupRequestPayload, { responseType: 'text' })
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<any> {
    const url = `${this.baseUrl}/auth/login`
    return this.http.post(url, loginRequestPayload, { responseType: 'text' })
  }

  checkForUsername(username: String): Observable<any> {
    const url = `${this.baseUrl}/auth/user/${username}`
    return this.http.get(url, { responseType: 'text' })
  }

  findAllUsers(): Observable<any> {
    const url = `${this.baseUrl}/auth/users`
    return this.http.get(url)
  }
}
