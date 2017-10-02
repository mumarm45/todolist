import { Injectable } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
@Injectable()
export class TaskService {
private  url = 'http://localhost:9092/api/task';
  constructor(private http: Http) { }

   get() {
     const headers = new Headers ({
       'Content-Type': 'application/json',
       'Access-Control-Allow-Origin': '*',
       'vary':'Origin'
     });
    return  this.http.get(this.url,headers).map(response => {
     return response.json();
     });
   }
   create(task) {
     const headers = new Headers ({
       'Content-Type': 'application/json',
       'Access-Control-Allow-Origin': '*'
     });
    return this.http.post(this.url, task,headers).map(res => {
       return res.json();
     });
   }
   remove(id) {
     return this.http.delete(`${this.url}/${id}`).map(res => {
            return res.json();
     });
   }
   update(id, data) {
     return this.http.put(`${this.url}/${id}`, data).map(res => {
           return res.json();
     });
   }

}
