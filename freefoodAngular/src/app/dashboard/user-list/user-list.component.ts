import { Component, OnInit, ViewChild } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MatSnackBar, MatDialog, MatTableDataSource, MatPaginator } from '@angular/material';
import { Usuario } from 'src/app/interfaces/usuario.interface';
import { ListaRecetasResponse } from 'src/app/interfaces/ListaRecetasResponse.interface';
import { CountRowListResponse } from 'src/app/interfaces/CountRowList';
import { DialogUserDeleteComponent } from '../dialog-user-delete/dialog-user-delete.component';
import { DialogEditUsuarioComponent } from '../dialog-edit-usuario/dialog-edit-usuario.component';

const ELEMENT_DATA: Usuario[] = [];

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = ['id','name', 'email', 'role', 'acciones'];  
  //dataSource: UsuarioInterface[];
  //dataSource = new MatTableDataSource(ELEMENT_DATA);
  dataSource;
  usuarioList: Usuario[];
  usuario: Usuario;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private usuarioService: UsuarioService, public snackBar: MatSnackBar, public dialog: MatDialog) { }

  ngOnInit() {
    this.getListaUsuarios('Lista de usuarios cargado');
  }


  getListaUsuarios(mensaje:string){
    this.usuarioService.getAllUsers().subscribe(listaUsuarios => {
      this.dataSource = listaUsuarios['rows'];
      //this.dataSource = new MatTableDataSource(listaUsuarios);
      this.dataSource.paginator = this.paginator;

      this.snackBar.open(mensaje, 'X',{
        duration:2000,
        verticalPosition: 'top'
      });

    }, error => {
      this.snackBar.open('Error al obtener usuarios', 'X',{
        duration:1000
      });
    });
  }

  openDialogDeleteUser(user: Usuario){
    const dialogNewRecipe = this.dialog.open(DialogUserDeleteComponent,{
      height: "40%",
      data:{
        element: user
      }
    });
    
    dialogNewRecipe.afterClosed().subscribe(resultado =>{
      
      this.getListaUsuarios("");
    })
  }


  openDialogEditUser(user: Usuario){
    const dialogEditRecipe = this.dialog.open(DialogEditUsuarioComponent,{
      height: "40%",
      data: {
        element: user
      }
    });
  
  dialogEditRecipe.afterClosed().subscribe(resultado =>{
    
    this.getListaUsuarios("");
  })
  }


}
