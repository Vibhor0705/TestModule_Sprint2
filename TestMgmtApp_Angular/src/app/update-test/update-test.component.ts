import { Component, OnInit } from '@angular/core';
import { Test } from '../model/Test';
import { Identifiers } from '@angular/compiler';
import { TestService } from '../services/testservice';

@Component({
  selector: 'app-update-test',
  templateUrl: './update-test.component.html',
  styleUrls: ['./update-test.component.css']
})
export class UpdateTestComponent implements OnInit {

  service:TestService;
  errMsg=null;
  constructor(service:TestService) {
    this.service=service;
   }
  
  updatedTest:Test=null;
  ngOnInit(): void {
  }
  
  
   updateTest(form:any)
   {
    let data= form.value;
    let testId = data.testId;
    let testTitle= data.testTitle;
    let testTotalMarks= data.testTotalMarks;
    let startTime = data.startTime;
   let endTime = data.endTime;
     this.updatedTest = new Test();
     this.updatedTest.testId =testId;
    this.updatedTest.testTitle=testTitle;
    this.updatedTest.testTotalMarks=testTotalMarks;
    this.updatedTest.startTime=startTime;
    this.updatedTest.endTime=endTime;
     let result = this.service.updateTest(this.updatedTest); // adding to the store
     result.subscribe((test: Test) => {
       this.updatedTest = test;
     },
       err => {
        this.errMsg=err.error;
         //console.log("err=" + err);
       });
     form.reset();
   }





}
