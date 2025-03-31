import { Component, OnInit } from "@angular/core";
import { ProductsApiService } from "./products-api.service";

@Component({
  selector: "app-products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css"],
})
export class ProductsComponent implements OnInit {
  products = [];
  // [
  //   { name: "Laptop", price: 1000, imageUrl: "assets/images/laptop.jpeg" },
  //   { name: "Phone", price: 500, imageUrl: "assets/images/phone.jpeg" },
  //   { name: "T-Shirt", price: 20, imageUrl: "assets/images/t-shirt.jpeg" },
  //   { name: "Jeans", price: 40, imageUrl: "assets/images/jeans.jpeg" },
  //   { name: "Microwave", price: 150, imageUrl: "assets/images/microwave.jpeg" },
  //   {
  //     name: "Vacuum Cleaner",
  //     price: 200,
  //     imageUrl: "assets/images/vacuum-cleaner.jpeg",
  //   },
  // ];

  isLoading: boolean = false;

  constructor(private productsApiService: ProductsApiService) {}

  ngOnInit() {
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
}
