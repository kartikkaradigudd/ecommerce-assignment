import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/shared/auth.service";
import { LoginApiService } from "./login-api.service";
import { LoginRequest } from "./login.model";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  username = "";
  password = "";

  constructor(
    private authService: AuthService,
    private loginApiService: LoginApiService
  ) {}
  ngOnInit() {}

  onLogin() {
    const loginRequest: LoginRequest = {
      userName: this.username,
      password: this.password,
    };

    this.loginApiService.login(loginRequest).subscribe((response) => {
      console.log("Login Response===> ", response);
      if (response.status === "OK") {
        this.authService.login(response.content.user);
        alert(response.content.message);
      } else {
        alert(response.content.message);
      }
    });
  }
}
