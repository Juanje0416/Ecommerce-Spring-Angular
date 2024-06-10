import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/common/order';
import { OrderCrudService } from 'src/app/services/order-crud.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  order: Order | null = null;

  constructor(
    private route: ActivatedRoute,
    private orderCrudService: OrderCrudService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const orderId = +params.get('id')!;
      this.getOrderDetails(orderId);
    });
  }

  getOrderDetails(orderId: number): void {
    this.orderCrudService.getOrderByid(orderId).subscribe(
      data => {
        this.order = data;
        console.log(data); // Log the order details for debugging
        swal(`Detalles del pedido número: ${this.order.id}`, '', 'success');

      }
    );
  }

  getOrderTotal(): number {
    if (!this.order) {
      return 0;
    }

    return this.order.orderProducts.reduce((total, orderProduct) => {
      return total + (orderProduct.price * orderProduct.quantity);
    }, 0);
  }
}
