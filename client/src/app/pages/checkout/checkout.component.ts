import { Component, OnInit } from "@angular/core";
import { CartApiService } from "../cart/cart-api.service";
import { AuthService } from "src/app/shared/auth.service";
import { CheckoutApiService } from "./checkout-api.service";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.css"],
})
export class CheckoutComponent implements OnInit {
  cartItems: any[] = [];
  totalPrice: number = 0;
  isLoading: boolean = true;
  username: string;
  discount: number = 0;
  discountedTotal: number = 0;
  discountCode: string = "";
  couponApplied: boolean = false;

  constructor(
    private cartService: CartApiService,
    private authService: AuthService,
    private checkoutApiService: CheckoutApiService
  ) {}

  ngOnInit() {
    this.username = this.authService.getUsername();
    this.fetchCartItems();
    // this.applyDiscountCode();
  }

  fetchCartItems() {
    this.isLoading = true;
    this.cartService
      .getCartDetailsByUsername(this.username)
      .subscribe((response) => {
        if (response.status === "OK") {
          this.cartItems = response.content.cart.products;
          this.checkoutApiService
            .validateOrder(this.username)
            .subscribe((validationResponse) => {
              if (validationResponse.status === "OK") {
                console.log("Validation Response=====>", validationResponse);
                if (validationResponse.content.validForCoupon) {
                  this.couponApplied = true;
                  this.discountCode = validationResponse.content.couponCode;
                } else {
                  this.couponApplied = false;
                }
                this.calculatePrices();
                this.isLoading = false;
              }
            });
        }
      });
  }

  calculatePrices() {
    this.totalPrice = this.cartItems.reduce((sum, item) => sum + item.price, 0);
    if (this.couponApplied) {
      this.discount = this.totalPrice * 0.1;
      this.discountedTotal = this.totalPrice - this.discount;
    } else {
      this.discount = 0;
      this.discountedTotal = this.totalPrice;
    }
  }

  // applyDiscountCode() {
  //   const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  //   this.discountCode =
  //     "OFF10-" +
  //     Array.from(
  //       { length: 6 },
  //       () => characters[Math.floor(Math.random() * characters.length)]
  //     ).join("");
  //   this.couponApplied = true;
  //   this.calculatePrices();
  // }

  proceedToPayment() {
    alert("Proceeding to payment...");
  }
}
