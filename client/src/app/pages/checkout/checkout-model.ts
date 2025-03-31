export class OrderRequest {
  username: string;
  productIds: Array<number>;
  totalPaidAmount: number;
  totalDiscount: number;
  couponCode: string;
}
