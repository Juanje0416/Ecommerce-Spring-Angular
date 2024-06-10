import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { UserType } from 'src/app/common/user-type';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { UserCrudService } from 'src/app/services/user-crud.service';
import { UserService } from 'src/app/services/user.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  id : Number = 0;
  username: string = '';
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  address: string = '';
  cellphone: string = '';
  password: string = '';
  userType: UserType = UserType.USER; // Inicializar a USER como predeterminado



  constructor(private authentication: AuthenticationService,
    private router: Router,
    private userService: UserService,
    private activatedRoute:ActivatedRoute,
    private sessionStorage:SessionStorageService
  ) {}

  register() {
    this.username = this.email;
    let user = new User(0, this.username, this.firstName, this.lastName, this.email, this.address, this.cellphone, this.password, this.userType);

    this.authentication.register(user).subscribe(
      res => {
        swal('Usuario creado', `El usuario: ${this.username} se ha creado correctamente`, 'success');
        console.log(res);
        this.router.navigate(['admin/user']);
      },
      err => {
        swal('Error', 'Hubo un problema al crear el usuario', 'error');
        console.error(err);
      }
    );

    console.log(user);
  }

  getUserById() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.userService.getUserById(id).subscribe(
          data => {
            this.id = id; // Asignar el valor de id al componente
            this.username = data.username;
            this.firstName = data.firstName;
            this.lastName = data.lastName;
            this.email = data.email;
            this.address = data.address;
            this.cellphone = data.cellphone;
            this.password = data.password;
            this.userType = data.userType as UserType; // Asegurando que el tipo sea UserType
          },
          err => {
            console.error('Error al obtener el usuario:', err);
          }
        );
      }
    });
  }
  

  ngOnInit(): void {
    this.getUserById();
    const sessionData = this.sessionStorage.getItem('token');
    console.log(sessionData);  
  }



}
