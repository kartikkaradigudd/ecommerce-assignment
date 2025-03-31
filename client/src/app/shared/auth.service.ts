import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "../pages/login/login.model";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private userRole: string | null = null;
  private username: string | null = null;

  constructor(private router: Router) {}

  login(user: User) {
    this.userRole = user.role;
    this.username = user.username;
    localStorage.setItem("role", this.userRole);
    localStorage.setItem("username", this.username);
    this.router.navigate(["/products"]);
    return true;
  }

  logout() {
    this.userRole = null;
    this.username = null;
    localStorage.removeItem("role");
    localStorage.removeItem("username");
    this.router.navigate(["/login"]);
  }

  getRole(): string | null {
    return localStorage.getItem("role");
  }

  getUsername(): string | null {
    return localStorage.getItem("username");
  }

  isAdmin(): boolean {
    return this.getRole().toLowerCase() === "admin";
  }
}
