import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SignupRequestPayload } from './signup/signup.request.payload';

import { LoginRequestPayload } from './login/login.request.payload';
import { map } from 'rxjs/operators';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginResponse } from './login/login.response.payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: String = 'http://localhost:8080/api'

  constructor(private http: HttpClient, private localStorage: LocalStorageService) { }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    const url = `${this.baseUrl}/auth/signup`
    return this.http.post(url, signupRequestPayload, { responseType: 'text' })
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    const url = `${this.baseUrl}/auth/login`
    return this.http.post<LoginResponse>(url, loginRequestPayload).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken)
      this.localStorage.store('username', data.username)
      this.localStorage.store('refreshToken', data.refreshToken)
      this.localStorage.store('expiresAt', data.expiresAt)

      return true
    }))
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
