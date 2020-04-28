import { Component, OnInit } from '@angular/core';
import { FreeApiService } from '../services/freeapiservice'
import { Comments } from '../classes/comment'
import { interval, throwError } from 'rxjs';

@Component({
  selector: 'app-addtodo',
  templateUrl: './addtodo.component.html',
  styleUrls: ['./addtodo.component.scss']
})
export class AddtodoComponent implements OnInit {

  constructor(private _freeApiService: FreeApiService){

  }
  
  listcomments: Comments[];

  ngOnInit(){
    
  }

  addTodo(title, description, duedate){
    var upDate1 = new Date();
    var dd = String(upDate1.getDate()).padStart(2, '0');
    var mm = String(upDate1.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = upDate1.getFullYear();
    console.log(yyyy+"/"+mm+"/"+dd);
    // upDate1 = Date.parse(yyyy+"/"+mm+"/"+dd);
    console.log(upDate1);
    var newdate = duedate.split("T");
    var newDate1 = Date.parse(newdate[0].replace(/-/g,"/"));
    var diff = newDate1;
    console.log(diff);
    if(diff > 0){
      let obj = {
          title: title,
          description: description,
          dueDate: duedate
      }

      this._freeApiService.addComments(obj).subscribe(
          () => console.log(`New Todo item added`),
          (err) => alert(JSON.stringify(err.error.message))            
      );
      this.getTodo();
    } 
  
    else{
      alert("Due date has passed. Please make a new selection")
    }
}
  getTodo(){
    this._freeApiService.getComments().subscribe(
        todos =>{
            this.listcomments = todos;
        });
  }

}
