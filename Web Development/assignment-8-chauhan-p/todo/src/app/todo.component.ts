import { Component } from '@angular/core';
import { FreeApiService } from './services/freeapiservice'
import { Comments } from './classes/comment'
import { interval, throwError } from 'rxjs';
@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class ToDoComponent{
    title =  "Welcome to my ToDo List";
    
    constructor(private _freeApiService: FreeApiService){

    }
    
    
    showVar: boolean = true;

    toggleChild(){
        this.showVar = !this.showVar;
    }
    listcomments: Comments[];
    //Refresh and default getTODO
    ngOnInit(){
        interval(1000).subscribe(
            (val) => { this.getTodo() },
            (err) => alert(JSON.stringify(err.error.message))
        );
    }
    getTodo(){
        this._freeApiService.getComments().subscribe(
            todos =>{
                this.listcomments = todos;
        });
    }
}
