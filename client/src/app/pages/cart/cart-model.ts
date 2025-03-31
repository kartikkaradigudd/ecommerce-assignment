import { Product } from "../products/product-model";

export class RemoveProductRequest {
  username: string;
  productId: number;
}

export class AddToCartRequest {
  username: string;
  product: Product;
}
