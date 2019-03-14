import { Component, OnInit, ViewChild } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MatSnackBar, MatDialog, MatTableDataSource, MatPaginator } from '@angular/material';
import { Usuario } from 'src/app/interfaces/usuario.interface';
import { ListaRecetasResponse } from 'src/app/interfaces/ListaRecetasResponse.interface';
import { CountRowListResponse } from 'src/app/interfaces/CountRowList';

//const ELEMENT_DATA: Usuario[] = [];

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = ['id','name', 'email', 'acciones'];  
  //dataSource: UsuarioInterface[];
  //dataSource = new MatTableDataSource(ELEMENT_DATA);
  dataSource;
  usuarioList: Usuario[];
  usuario: CountRowListResponse;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private usuarioService: UsuarioService, public snackBar: MatSnackBar, public dialog: MatDialog) { }

  ngOnInit() {
    this.getListaUsuarios('Lista de usuarios cargado');
  }


  getListaUsuarios(mensaje:string){
    this.usuarioService.getAllUsers().subscribe(listaUsuarios => {
      //this.dataSource.data = listaUsuarios['rows'];
      this.dataSource = new MatTableDataSource(listaUsuarios.rows);
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


}
