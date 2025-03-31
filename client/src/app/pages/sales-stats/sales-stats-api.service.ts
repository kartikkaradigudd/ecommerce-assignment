import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ClientConfigurations, httpHeaders } from "src/app/configs/configs";

@Injectable({
  providedIn: "root",
})
export class SalesStatsApiService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = ClientConfigurations.baseUrl;
  }

  getSalesStats(): Observable<any> {
    return this.http.get<any>(this.url + "admin/getSalesStats", {
      headers: httpHeaders.header,
    });
  }
}
