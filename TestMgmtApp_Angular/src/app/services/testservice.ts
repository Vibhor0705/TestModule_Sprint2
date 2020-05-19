import { Injectable } from '@angular/core';
import { Observable, observable } from 'rxjs';
import { Test } from '../model/Test';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';

@Injectable()
export class TestService{
  client:HttpClient ;
  constructor(client:HttpClient ){
  this.client=client;
  }

   baseTestUrl="http://localhost:8087/tests";


  addTest(test:Test): Observable<Test>{
  let url=this.baseTestUrl+"/add";
  let result:Observable<Test>= this.client.post<Test>(url,test);
  return result;
  }
  

  updateTest(test:Test): Observable<Test>{
    let url=this.baseTestUrl+"/update";
    let result:Observable<Test>= this.client.put<Test>(url,test);
    return result;
    }

 
  findTestById(id:number):Observable<Test>{
    let url=this.baseTestUrl+'/find/'+id;
    let observable:Observable<Test> =this.client.get<Test>(url);
    return observable;  
  }
  

  addUser(user:User): Observable<User>{
    let url=this.baseTestUrl+"/addUser";
    let result:Observable<User>= this.client.post<User>(url,user);
    return result;
    }
 
  deleteTestById(id:number){
    let url=this.baseTestUrl+"/remove/"+id;
    let result:Observable<Test>=this.client.get<Test>(url);
    return result;
  }

  fetchAllTest():Observable<Test[]>
  {
    let url=this.baseTestUrl;
    let observable:Observable<Test[]> =this.client.get<Test[]>(url);
    return observable;
  }

}