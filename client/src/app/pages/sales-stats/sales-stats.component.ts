import { Component, OnInit } from "@angular/core";
import { SalesStatsApiService } from "./sales-stats-api.service";

@Component({
  selector: "app-sales-stats",
  templateUrl: "./sales-stats.component.html",
  styleUrls: ["./sales-stats.component.css"],
})
export class SalesStatsComponent implements OnInit {
  totalItems: number = 0;
  totalAmount: number = 0;
  discountCodes = [
    // { code: "SAVE10", amount: 10 },
    // { code: "OFFER20", amount: 20 },
  ];
  totalDiscount: number = 0;

  isLoading: boolean = false;

  constructor(private salesStatsApiService: SalesStatsApiService) {}

  ngOnInit() {
    this.loadStats();
  }

  loadStats() {
    this.isLoading = true;
    this.salesStatsApiService.getSalesStats().subscribe((response) => {
      if (response.status === "OK") {
        this.totalItems = response.content.noOfItemsPurchased;
        this.totalAmount = response.content.totalPurchaseAmount;
        this.discountCodes = response.content.couponCodes;
        this.isLoading = false;
      } else {
        alert(response.content.message);
      }
    });
  }
}
