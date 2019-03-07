import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PrincipalComponent } from './paginas/principal/principal.component';
import { BuscarVehiculoComponent } from './paginas/buscar-vehiculo/buscar-vehiculo.component';
import { LoginComponent } from './paginas/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MultarComponent } from './paginas/multar/multar.component';
import { VerMultasComponent } from './paginas/ver-multas/ver-multas.component';
import { Error404Component } from './paginas/error404/error404.component';
import { AlertComponent } from './components/alert/alert.component';
import { ObjetivosComponent } from './paginas/objetivos/objetivos.component';

@NgModule({
  declarations: [
    AppComponent,
    PrincipalComponent,
    BuscarVehiculoComponent,
    LoginComponent,
    NavbarComponent,
    MultarComponent,
    VerMultasComponent,
    Error404Component,
    AlertComponent,
    ObjetivosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, // Para usarlo con los services
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
