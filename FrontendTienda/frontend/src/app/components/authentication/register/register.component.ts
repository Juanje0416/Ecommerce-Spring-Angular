import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { UserType } from 'src/app/common/user-type';
import { AuthenticationService } from 'src/app/services/authentication.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegistrationComponent implements OnInit{

  username : string = ''
  firstName : string = ''
  lastName : string = ''
  email : string = ''
  address : string = ''
  cellphone : string = ''
  password : string = ''
  userType : string = ''

  constructor(private authentication : AuthenticationService, private router : Router){

  }

  

  register(){
    this.username = this.email
    this.userType = UserType.USER
    let user = new User(0, this.username, this.firstName, this.lastName, this.email, this.address, this.cellphone, this.password, this.userType);

    this.authentication.register(user).subscribe(
      res =>
        {
         console.log(res)
        }

    )
    console.log(user)
    this.router.navigate(['user/login'])
  }
  
  ngOnInit(): void {

  }



}
