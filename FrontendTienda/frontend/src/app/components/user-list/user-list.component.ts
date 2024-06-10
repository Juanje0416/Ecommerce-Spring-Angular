import { Component } from '@angular/core';
import { User } from 'src/app/common/user';
import { UserCrudService } from 'src/app/services/user-crud.service';
import { UserService } from 'src/app/services/user.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  
  users : User[] = [];
  p: number = 1;
  constructor(private userCrudService:UserCrudService){

  }
  
  ngOnInit(): void {
    this.listUsers();
  }

  listUsers(){
    this.userCrudService.getUsers().subscribe(
      data => {this.users = data
        console.log(data);//esto es para que me muestre la información en el inspeccionar
      }
    );
  }

  deleteUserById(id: number) {
    // Primero obtenemos el usuario por su ID
    this.userCrudService.getUserById(id).subscribe(
        (user) => {
            // Ahora que tenemos el usuario, procedemos a la confirmación
            swal({
                title: '¿Estás seguro?',
                text: `Confirma si deseas eliminar el usuario ${user.username}`,
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, eliminarlo',
                cancelButtonText: 'No, cancelar',
                confirmButtonClass: 'btn btn-success',
                cancelButtonClass: 'btn btn-danger',
                buttonsStyling: true
            }).then((result) => {
                if (result.value) {
                    // Si se confirma, eliminamos el usuario
                    this.userCrudService.deleteUserById(id).subscribe(
                        () => {
                            this.listUsers();
                            swal(
                                'Usuario eliminado',
                                `El usuario: ${user.username} ha sido eliminado con éxito`,
                                'success'
                            );
                        }
                    );
                }
            });
        },
        (error) => {
            // Manejo de error si no se puede obtener el usuario
            swal(
                'Error',
                'No se pudo obtener la información del usuario. Por favor, intente nuevamente.',
                'error'
            );
        }
    );
}

  
}