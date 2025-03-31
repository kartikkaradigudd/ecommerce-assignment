import { Component, OnInit } from "@angular/core";
import { CartApiService } from "./cart-api.service";
import { AuthService } from "src/app/shared/auth.service";
import { RemoveProductRequest } from "./cart-model";
import { Product } from "../products/product-model";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"],
})
export class CartComponent implements OnInit {
  products: Array<Product> = [];
  username: string;

  isLoading: boolean = false;

  constructor(
    private cartApiService: CartApiService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.username = this.authService.getUsername();

    this.loadCartDetails();
  }

  loadCartDetails() {
    this.isLoading = true;
    this.cartApiService
      .getCartDetailsByUsername(this.username)
      .subscribe((response) => {
        if (response.status === "OK") {
          console.log("Cart Details ==> ", response);
          this.products = response.content.cart.products;
          this.isLoading = false;
        } else {
          alert(response.content.message);
        }
      });
  }

  removeProduct(index) {
    console.log("Removing Product from Cart===> ", this.products[index]);
    const removeProductRequest: RemoveProductRequest = {
      username: this.username,
      productId: this.products[index].id,
    };

    this.cartApiService
      .removeProductFromCart(removeProductRequest)
      .subscribe((response) => {
        if (response.status === "OK") {
          this.products.splice(index, 1);
          alert(response.content.message);
        } else {
          alert(response.content.message);
        }
      });
  }

  proceedToCheckout() {
    console.log("Proceeding with the products===> ", this.products);
  }
}
