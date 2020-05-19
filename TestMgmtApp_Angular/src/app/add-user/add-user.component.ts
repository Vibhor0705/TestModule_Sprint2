import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { TestService } from '../services/testservice';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  service:TestService;
  constructor(service:TestService) {
    this.service=service;
   }
 addedUser:User=null;
  ngOnInit(): void {
  }

  register(form:any){
    let data= form.value;
    let userName= data.userName;
    let userPassword= data.userPassword;
    let isAdmin = data.isAdmin;
   this.addedUser = new User();
    this.addedUser.userName=userName;
    this.addedUser.isAdmin=isAdmin;
    let result = this.service.addUser(this.addedUser); // adding to the store
    result.subscribe((user: User) => {
      this.addedUser = user;
    },
      err => {
        console.log("err=" + err);
      });
      
   form.reset();
   
   }
}
