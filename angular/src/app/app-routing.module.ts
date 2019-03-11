import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Componentes
import { BuscarVehiculoComponent } from './paginas/buscar-vehiculo/buscar-vehiculo.component';
import { PrincipalComponent } from './paginas/principal/principal.component';
import { LoginComponent } from './paginas/login/login.component';
import { MultarComponent } from './paginas/multar/multar.component';
import { VerMultasComponent } from './paginas/ver-multas/ver-multas.component';
import { Error404Component } from './paginas/error404/error404.component';
import { ObjetivosComponent } from './paginas/objetivos/objetivos.component';

// Guards
import { LoginGuard } from './guards/login.guard';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'buscar-vehiculo', component: BuscarVehiculoComponent, canActivate: [LoginGuard] },
  { path: 'principal', component: PrincipalComponent, canActivate: [LoginGuard] },
  { path: 'ver-multas/:estado', component: VerMultasComponent, canActivate: [LoginGuard] },
  { path: 'multar', component: MultarComponent, canActivate: [LoginGuard] },
  { path: 'objetivos', component: ObjetivosComponent, canActivate: [LoginGuard] },
  { path: '404', component: Error404Component },
  { path: '', pathMatch: 'full', redirectTo: 'principal'},
  { path: '**', pathMatch: 'full', redirectTo: '404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
