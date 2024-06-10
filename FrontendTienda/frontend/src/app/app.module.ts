import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HeaderAdminComponent } from './components/header-admin/header-admin.component';
import { Routes, RouterModule } from '@angular/router';
import { ProductAddComponent } from './components/product-add/product-add.component';
import { FormsModule } from '@angular/forms';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CategoryListComponent } from './components/category/category-list/category-list.component';
import { CategoryAddComponent } from './components/category/category-add/category-add.component';
import { DetailProductComponent } from './components/cart/detail-product/detail-product.component';
import { HeaderUserComponent } from './components/header-user/header-user.component'; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { SumaryOrderComponent } from './components/orders/sumary-order/sumary-order.component';
import { PaymentSuccesComponent } from './components/payment-succes/payment-succes.component';
import { RegistrationComponent } from './components/authentication/register/register.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { authGuard } from './guards/auth.guard';
import { BannerComponent } from './components/banner/banner.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserAddComponent } from './components/user-add/user-add.component';
import { ZapatosComponent } from './components/paginas-productos/zapatos/zapatos.component';
import { CamisetasComponent } from './components/paginas-productos/camisetas/camisetas.component';
import { PantalonesComponent } from './components/paginas-productos/pantalones/pantalones.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { OfertasComponent } from './components/paginas-productos/ofertas/ofertas.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component'; // <-- import the module




const routes:Routes = [
  {path:'',component:HomeComponent},
  {path:'admin/product',component: ProductListComponent},
  {path:'admin/product/addproduct', component:ProductAddComponent},
  {path:'admin/product/update/:id', component:ProductAddComponent},
  {path:'admin/product/details/:id', component:ProductDetailsComponent},
  {path:'admin/user',component: UserListComponent},
  {path:'admin/user/adduser',component: UserAddComponent},
  {path:'admin/user/update/:id', component:UserAddComponent},
  {path:'admin/category', component:CategoryListComponent},
  {path:'admin/category/add', component:CategoryAddComponent},
  {path:'admin/category/update/:id', component:CategoryAddComponent},
  {path:'cart/detailproduct/:id', component:DetailProductComponent},
  {path:'cart/sumary', component:SumaryOrderComponent, canActivate: [authGuard]}, //implemento mi guard que me permite hacer que si un usuario no está logado no le deja y lo redirecciona al login
  {path:'payment/success', component:PaymentSuccesComponent},
  {path:'user/register', component:RegistrationComponent},
  {path:'user/login', component:LoginComponent},
  {path:'logout', component:LogoutComponent},
  {path:'shirts', component:CamisetasComponent},
  {path:'shoes', component:ZapatosComponent},
  {path:'pants', component:PantalonesComponent},
  {path:'offers', component:OfertasComponent},
  {path:'admin/ordercrud', component:OrderListComponent},
  {path:'admin/ordercrud/details/:id', component:OrderDetailsComponent}


];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductListComponent,
    HeaderAdminComponent,
    ProductAddComponent,
    ProductDetailsComponent,
    CategoryListComponent,
    CategoryAddComponent,
    DetailProductComponent,
    HeaderUserComponent,
    SumaryOrderComponent,
    PaymentSuccesComponent,
    RegistrationComponent,
    LoginComponent,
    LogoutComponent,
    BannerComponent,
    OrderListComponent,
    UserListComponent,
    UserAddComponent,
    ZapatosComponent,
    CamisetasComponent,
    PantalonesComponent,
    OfertasComponent,
    OrderDetailsComponent,
  ],
  imports: [
    BrowserModule,
    NgxPaginationModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
