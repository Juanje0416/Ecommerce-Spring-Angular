import { Component, OnInit } from '@angular/core';
import { OrderState } from 'src/app/common/order-state';
import { OrderService } from 'src/app/services/order.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-payment-succes',
  templateUrl: './payment-succes.component.html',
  styleUrls: ['./payment-succes.component.css']
})
export class PaymentSuccesComponent implements OnInit{
  
  constructor(
    private orderService:OrderService,
    private sessionStorage:SessionStorageService
  ){

  }

  //cambio en la BBDD
  ngOnInit(): void {
    console.log(this.sessionStorage.getItem('order'))
    let order = this.sessionStorage.getItem('order')

    let formData = new FormData();

    formData.append('id', order.id);
    formData.append('state', OrderState.CONFIRMED.toString());

    this.orderService.updateOrder(formData).subscribe(
      data=>console.log(data)
    )


  }

  
}
