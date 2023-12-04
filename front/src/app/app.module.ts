import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { HomePageComponent } from './home-page/home-page.component';
import { AppRoutingModule } from 'src/app-routing.module';
import { RegisterComponent } from './register/register.component';
import { UserInterfaceComponent } from './user-interface/user-interface.component';
import { AdminInterfaceComponent } from './admin-interface/admin-interface.component';
import { HeaderComponent } from './header/header.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { LabInterfaceComponent } from './lab-interface/lab-interface.component';
import { SearchPipe } from './search.pipe';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    RegisterComponent,
    UserInterfaceComponent,
    AdminInterfaceComponent,
    HeaderComponent,
    SideNavComponent,
    LabInterfaceComponent,
    SearchPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxDatatableModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
