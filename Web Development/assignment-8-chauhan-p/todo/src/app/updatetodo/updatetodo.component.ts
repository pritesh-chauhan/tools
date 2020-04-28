import { Component, OnInit, Input } from '@angular/core';
import { FreeApiService } from '../services/freeapiservice'
import { Comments } from '../classes/comment'
import { interval, throwError } from 'rxjs';


@Component({
  selector: 'app-updatetodo',
  templateUrl: './updatetodo.component.html',
  styleUrls: ['./updatetodo.component.scss']
})
export class UpdatetodoComponent implements OnInit {
  @Input()
  Todo: string;

  constructor(private _freeApiService: FreeApiService){

  }

  ngOnInit(): void {
  }

  listcomments: Comments[];

  updateTodo(listcomments){
      console.log(listcomments.id);
      var title = prompt("Title:", listcomments.title);
      if(title != null){
          var description = prompt("Description:", listcomments.description);
          if(description != null){
              var check = prompt("Check:", listcomments.check);
              var ch = 0;
              if(check == 'true')
                ch = 1;
              else
                ch = -1; 
              var unch = 0;
              if(listcomments.check)
                unch = 1;
              else
                unch = -1;
          if(check != null){
              var duedate = prompt("Due Date:", listcomments.dueDate);
              if(duedate != null){
                if ((title != listcomments.title || description != listcomments.description || (ch != unch) || duedate != listcomments.dueDate) && (title != "" && description != "" && check != "" && duedate != "")) {
                    console.log("Both updated");
                    console.log(title != listcomments.title);
                    console.log(description != listcomments.description);
                    console.log(check,ch,"----->", listcomments.check,unch,"------>",(ch != unch));


                    var newdate = duedate.split("T");
                    var update = (listcomments.updatedAt).split("T"); 
                    var newDate1 = Date.parse(newdate[0].replace(/-/g,"/"));
                    var upDate1 = Date.parse(update[0].replace(/-/g,"/"));
                    var diff = newDate1 - upDate1;
                    
                    if(diff > 0){
                      console.log((newDate1 - upDate1));
                      alert("Updated Values:\nTitle:"+title + "\nDescription:" + description + "\nCheck:" + check + "\nDue Date:" + duedate);
                      let obj = {
                          title: title,
                          description: description,
                          check: check,
                          dueDate: duedate
                      }
                      
                      this._freeApiService.updateComments(obj, listcomments.id).subscribe(
                          () => console.log(`Todo item with id: ${listcomments.id} updated`),
                          (err) => alert(JSON.stringify(err.error.message))            
                      );
                      this.getTodo();
                    }
                    else{
                      alert("Due date has passed");
                      console.log("Due date has passed");
                    }
                } 
                else{
                    alert("Nothing Updated");
                    console.log("Nothing updated");
                }
            }
          }
        }
      }
  }
  getTodo(){
    this._freeApiService.getComments().subscribe(
        todos =>{
            this.listcomments = todos;
        });
  }

}
