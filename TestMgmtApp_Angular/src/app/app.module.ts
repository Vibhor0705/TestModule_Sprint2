import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddTestComponent } from './add-test/add-test.component';
import { UpdateTestComponent } from './update-test/update-test.component';
import { FindTestComponent } from './find-test/find-test.component';
import { AddUserComponent } from './add-user/add-user.component';
import { HttpClientModule } from '@angular/common/http';
import { TestService } from './services/testservice';
import { ViewAllTestComponent } from './view-all-test/view-all-test.component';

@NgModule({
  declarations: [
    AppComponent,
    AddTestComponent,
    UpdateTestComponent,
    FindTestComponent,
    AddUserComponent,
    ViewAllTestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [TestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
