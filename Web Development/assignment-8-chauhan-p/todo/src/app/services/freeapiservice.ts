import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { HttpClient } from '@angular/common/http'

@Injectable({ providedIn: 'root' })
export class FreeApiService{

    constructor(private httpclient: HttpClient){}
    
    getComments(): Observable<any>{
        return this.httpclient.get("http://localhost:3000/todo");
    }

    deleteComments(id): Observable<any>{
        let del = this.httpclient.delete(`http://localhost:3000/todo/${id}`);
        let get_api = this.httpclient.get("http://localhost:3000/todo");
        return forkJoin([del, get_api]);
    }

    updateComments(obj, id): Observable<any>{
        console.log(obj);
        let upd = this.httpclient.put(`http://localhost:3000/todo/${id}`, obj);
        let get_api = this.httpclient.get("http://localhost:3000/todo");
        return forkJoin([upd, get_api]);
    }

    addComments(obj): Observable<any>{
        console.log(obj);
        let pst = this.httpclient.post(`http://localhost:3000/todo`, obj);
        let get_api = this.httpclient.get("http://localhost:3000/todo");
        return forkJoin([pst, get_api]);
    }
}