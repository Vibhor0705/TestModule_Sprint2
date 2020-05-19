import { Component, OnInit } from '@angular/core';
import { Test } from '../model/Test';
import { TestService } from '../services/testservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-all-test',
  templateUrl: './view-all-test.component.html',
  styleUrls: ['./view-all-test.component.css']
})
export class ViewAllTestComponent implements OnInit {
  allTest:Test[]=[];
  test:Test;
  service:TestService;
  
  constructor( service:TestService) {
    this.service=service;
    this.getAllTest();
   }
   
   private getAllTest(){
     let observable:Observable<Test[]>=this.service.fetchAllTest();
     observable.subscribe(fetch =>{
       this.allTest=fetch;
       console.log("inside sucess callback =" + this.allTest.length)
     }, err => console.log(err));
   }
  ngOnInit(): void {
  }
  removeTest(test:Test){
    let testId=test.testId;
    let result:Observable<Test>= this.service.deleteTestById(testId);
    result.subscribe(fetch =>{
      this.test=fetch;
      this.getAllTest();
      console.log("record deleted successfully for testId=" + fetch.testId)
    }, err => {
      console.log("err in deleting test record ="+ err)
    });
  }
}
