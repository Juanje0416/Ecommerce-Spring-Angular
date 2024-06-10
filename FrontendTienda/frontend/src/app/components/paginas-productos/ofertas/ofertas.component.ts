import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-ofertas',
  templateUrl: './ofertas.component.html',
  styleUrls: ['./ofertas.component.css']
})
export class OfertasComponent implements OnInit, AfterViewInit {
  products: Product[] = [];
  date: any;
  now: any;
  targetDate: any = new Date(2024, 5, 30);
  targetTime: any = this.targetDate.getTime();
  difference: number;
  months: Array<string> = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
  currentTime: any = `${this.months[this.targetDate.getMonth()]} ${this.targetDate.getDate()}, ${this.targetDate.getFullYear()}`;

  @ViewChild("days", { static: true }) days: ElementRef;
  @ViewChild("hours", { static: true }) hours: ElementRef;
  @ViewChild("minutes", { static: true }) minutes: ElementRef;
  @ViewChild("seconds", { static: true }) seconds: ElementRef;

  constructor(private homeService: HomeService) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  ngAfterViewInit() {
    setInterval(() => {
      this.tickTock();
      this.difference = this.targetTime - this.now;
      this.difference = this.difference / (1000 * 60 * 60 * 24);
      this.days.nativeElement.innerText = Math.floor(this.difference);
    }, 1000);
  }

  loadProducts(): void {
    const categoryId = 8;
    this.homeService.getProductsByCategoryId(categoryId).subscribe(
      data => this.products = data
    );
  }

  tickTock() {
    this.date = new Date();
    this.now = this.date.getTime();
    this.days.nativeElement.innerText = Math.floor(this.difference);
    this.hours.nativeElement.innerText = 23 - this.date.getHours();
    this.minutes.nativeElement.innerText = 60 - this.date.getMinutes();
    this.seconds.nativeElement.innerText = 60 - this.date.getSeconds();
  }
}
