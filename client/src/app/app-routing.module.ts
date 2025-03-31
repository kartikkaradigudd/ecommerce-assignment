import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule, Routes } from "@angular/router";
import { ProductsComponent } from "./pages/products/products.component";
import { CartComponent } from "./pages/cart/cart.component";
import { LoginComponent } from "./pages/login/login.component";
import { CheckoutComponent } from "./pages/checkout/checkout.component";

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "products", component: ProductsComponent },
  { path: "cart", component: CartComponent },
  // { path: 'sales-stats', component: SalesStatsComponent },
  // { path: 'generate-discount', component: GenerateDiscountComponent },
  { path: "checkout", component: CheckoutComponent },

  { path: "", redirectTo: "/login", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
