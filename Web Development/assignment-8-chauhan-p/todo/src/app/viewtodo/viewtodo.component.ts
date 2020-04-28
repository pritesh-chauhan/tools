import { Component, OnInit, Input } from '@angular/core';
import { FreeApiService } from '../services/freeapiservice'
import { Comments } from '../classes/comment'
import { interval, throwError } from 'rxjs';

@Component({
  selector: 'app-viewtodo',
  templateUrl: './viewtodo.component.html',
  styleUrls: ['./viewtodo.component.scss']
})
export class ViewtodoComponent implements OnInit {
  @Input()
  Todo: string;

  constructor(private _freeApiService: FreeApiService){

  }

  listcomments: Comments[];

  ngOnInit(): void {
    // console.log(this.Todo);
  }

  viewTodo(listcomments){
    alert("ToDo item values:\nTitle: "+listcomments.title + "\nDescription: " + listcomments.description + "\nCheck: " + listcomments.check + "\nCreated At: " + listcomments.createdAt + "\nUpdated At: " + listcomments.updatedAt + "\nDue Date: " + listcomments.dueDate);
  }

}
