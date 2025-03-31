import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css"],
})
export class ProductsComponent implements OnInit {
  products = [
    { name: "Laptop", price: 1000 },
    { name: "Phone", price: 500 },
    { name: "T-Shirt", price: 20 },
    { name: "Jeans", price: 40 },
    { name: "Microwave", price: 150 },
    { name: "Vacuum Cleaner", price: 200 },
  ];

  constructor() {}

  ngOnInit() {}
}
