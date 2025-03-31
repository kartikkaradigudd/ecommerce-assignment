import { Component, OnInit } from "@angular/core";
import { ProductsApiService } from "./products-api.service";
import { Product } from "./product-model";
import { AuthService } from "src/app/shared/auth.service";
import { AddToCartRequest } from "../cart/cart-model";
import { CartApiService } from "../cart/cart-api.service";

@Component({
  selector: "app-products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css"],
})
export class ProductsComponent implements OnInit {
  products: Array<Product> = [];
  username: string;
  isLoading: boolean = false;

  constructor(
    private productsApiService: ProductsApiService,
    private authService: AuthService,
    private cartApiService: CartApiService
  ) {}

  ngOnInit() {
    this.username = this.authService.getUsername();
    this.loadMetaData();
  }

  loadMetaData() {
    this.isLoading = true;
    this.productsApiService.getAllProducts().subscribe((response) => {
      if (response.status === "OK") {
        this.products = response.content.products;
        this.isLoading = false;
      } else {
        alert(response.content.message);
      }
    });
  }

  addToCart(product: Product) {
    const addToCartRequest: AddToCartRequest = {
      username: this.username,
      product: product,
    };

    this.cartApiService
      .addProductToCart(addToCartRequest)
      .subscribe((response) => {
        alert(response.content.message);
      });
  }
}
