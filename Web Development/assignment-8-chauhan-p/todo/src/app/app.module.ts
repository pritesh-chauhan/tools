import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { ToDoComponent } from './todo.component';

import { HttpClientModule } from '@angular/common/http'
import { FreeApiService } from './services/freeapiservice';
import { UpdatetodoComponent } from './updatetodo/updatetodo.component';
import { AddtodoComponent } from './addtodo/addtodo.component';
import { DeletetodoComponent } from './deletetodo/deletetodo.component';
import { ViewtodoComponent } from './viewtodo/viewtodo.component';

import { ModalComponent } from './modal/modal.component';

@NgModule({
  declarations: [
    ToDoComponent,
    UpdatetodoComponent,
    AddtodoComponent,
    DeletetodoComponent,
    ViewtodoComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [FreeApiService],
  
  bootstrap: [ToDoComponent]
})
export class AppModule { }
