import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ClientConfigurations, httpHeaders } from "src/app/configs/configs";

@Injectable({
  providedIn: "root",
})
export class CheckoutApiService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = ClientConfigurations.baseUrl;
  }

  validateOrder(username: string): Observable<any> {
    return this.http.get<any>(this.url + "checkout/validateOrder/" + username, {
      headers: httpHeaders.header,
    });
  }
}
