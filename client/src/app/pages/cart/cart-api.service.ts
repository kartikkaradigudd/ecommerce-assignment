import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ClientConfigurations, httpHeaders } from "src/app/configs/configs";
import { AddToCartRequest, RemoveProductRequest } from "./cart-model";

@Injectable({
  providedIn: "root",
})
export class CartApiService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = ClientConfigurations.baseUrl;
  }

  getCartDetailsByUsername(username): Observable<any> {
    return this.http.get<any>(
      this.url + "cart/getCartDetailsByUsername/" + username,
      {
        headers: httpHeaders.header,
      }
    );
  }

  removeProductFromCart(
    removeProductRequest: RemoveProductRequest
  ): Observable<any> {
    return this.http.post<any>(
      this.url + "cart/removeProductFromCart",
      removeProductRequest,
      {
        headers: httpHeaders.header,
      }
    );
  }

  addProductToCart(addProdcuctRequest: AddToCartRequest): Observable<any> {
    return this.http.post<any>(
      this.url + "cart/addProductToCart",
      addProdcuctRequest,
      {
        headers: httpHeaders.header,
      }
    );
  }
}
