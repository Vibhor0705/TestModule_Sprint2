import { Component, OnInit } from '@angular/core';
import { Test } from '../model/Test';
import { TestService } from '../services/testservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-find-test',
  templateUrl: './find-test.component.html',
  styleUrls: ['./find-test.component.css']
})
export class FindTestComponent implements OnInit {
  foundTest:Test=null;
  foundStatus=null;
  service:TestService;
  errMsg=null;
  constructor(service:TestService) {
    this.service=service;
   }
   ngOnInit(): void {
  }
  
   findTestById(form:any):void{
    let details=form.value;
    let testId=details.testId;
    let fetched:Observable<Test> =this.service.findTestById(testId);
   fetched.subscribe(
    test=>{
    this.foundTest=test; 
    this.foundStatus="found";
     },
    err=>{
      this.foundStatus="notfound";
      this.errMsg=err.error;
      
     console.log("err while fetching Test="+err);  
     }
   );    
  
  }


}
