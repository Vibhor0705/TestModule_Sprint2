import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddTestComponent } from './add-test/add-test.component';
import { FindTestComponent } from './find-test/find-test.component';
import { UpdateTestComponent } from './update-test/update-test.component';
import { AddUserComponent } from './add-user/add-user.component';
import { ViewAllTestComponent } from './view-all-test/view-all-test.component';


const routes: Routes = [ {path: "addTest", component: AddTestComponent},
{path: "findTest", component: FindTestComponent},
{path: "updateTest", component: UpdateTestComponent},
{path: "viewAllTest", component:ViewAllTestComponent},
{path: "addUser", component:  AddUserComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
