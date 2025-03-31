import { Injectable } from "@angular/core";
import { ClientConfigurations, httpHeaders } from "src/app/configs/configs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { LoginRequest } from "./login.model";

@Injectable({
  providedIn: "root",
})
export class LoginApiService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = ClientConfigurations.baseUrl;
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post<any>(this.url + "auth/login", loginRequest, {
      headers: httpHeaders.header,
    });
  }
}
