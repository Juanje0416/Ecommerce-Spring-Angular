import { Component } from '@angular/core';
import { Order } from 'src/app/common/order';
import { CartService } from 'src/app/services/cart.service';
import { OrderCrudService } from 'src/app/services/order-crud.service';
import { OrderService } from 'src/app/services/order.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent {
  orders : Order[] = [];
  totalCart : number = 0
  p: number = 1;

  
  constructor(private orderCrudService:OrderCrudService){

  }

  ngOnInit(): void {
    this.listOrders();
  }

  listOrders(){
    this.orderCrudService.getOrders().subscribe(
      data => {this.orders = data
        console.log(data);//esto es para que me muestre la información en el inspeccionar
      }
    );
  }

  
}