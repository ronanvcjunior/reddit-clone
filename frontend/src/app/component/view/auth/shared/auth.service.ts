import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SignupRequestPayload } from '../signup/signup.request.payload';

import { LoginRequestPayload } from '../login/login.request.payload';
import { map, tap } from 'rxjs/operators';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginResponse } from '../login/login.response.payload';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: String = environment.baseUrl;

  constructor(
        private http: HttpClient, 
        private localStorage: LocalStorageService,
        private _snack: MatSnackBar) { }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    const url = `${this.baseUrl}/api/auth/signup`
    return this.http.post(url, signupRequestPayload, { responseType: 'text' })
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    const url = `${this.baseUrl}/api/auth/login`
    return this.http.post<LoginResponse>(url, loginRequestPayload).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken)
      this.localStorage.store('username', data.username)
      this.localStorage.store('refreshToken', data.refreshToken)
      this.localStorage.store('expiresAt', data.expiresAt)

      return true
    }))
  }

  checkForUsername(username: String): Observable<any> {
    const url = `${this.baseUrl}/api/auth/user/${username}`
    return this.http.get(url, { responseType: 'text' })
  }

  findAllUsers(): Observable<any> {
    const url = `${this.baseUrl}/api/auth/users`
    return this.http.get(url)
  }

  mensagem(str: String): void {
    this._snack.open(`${str}`, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    })
  }

  mensagemWithTime(str: String, duration: number): void {
    this._snack.open(`${str}`, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration: duration
    })
  }

  refreshToken() {
    const url = `${this.baseUrl}/api/auth/refresh/token`

    const refreshTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName()
    }
    return this.http.post<LoginResponse>(url,
      refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.store('authenticationToken', response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  getJwtToken() {
    return this.localStorage.retrieve('authenticationToken');
  }

  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }

  getExpirationTime() {
    return this.localStorage.retrieve('expiresAt');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
