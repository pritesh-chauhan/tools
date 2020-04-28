import { Component, OnInit, Input } from '@angular/core';
import { FreeApiService } from '../services/freeapiservice'
import { Comments } from '../classes/comment'
import { interval, throwError } from 'rxjs';

@Component({
  selector: 'app-deletetodo',
  templateUrl: './deletetodo.component.html',
  styleUrls: ['./deletetodo.component.scss']
})
export class DeletetodoComponent implements OnInit {
  @Input()
  Todo: string;

  constructor(private _freeApiService: FreeApiService){

  }

  listcomments: Comments[];

  ngOnInit(): void {
  }
  
  getTodo(){
    this._freeApiService.getComments().subscribe(
        todos =>{
            this.listcomments = todos;
        });
  }

  deleteTodo(listcomments){
      console.log("ID to be deleted is: ",listcomments.id);
      this._freeApiService.deleteComments(listcomments.id).subscribe(
          () => console.log(`Todo item with id: ${listcomments.id} deleted`),
          (err) => alert(JSON.stringify(err.error.message))            
      );
      this.getTodo();
  }

}
