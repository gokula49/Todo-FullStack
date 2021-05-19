import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';

export class Todo {
  constructor(
    public id: number,
    public description : string,
   public targetdate : Date,
  public isDone : boolean,
  )
  {

  }
}
@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {
 todos : Todo[]
 message : string
  // todos = [
  //   new Todo(1,'kann',false,new Date()),
  //   new Todo(2,'krish',false,new Date()),
  //   new Todo(3,'krishnan',false,new Date()),
  //   new Todo(4,'kannan',false,new Date()),


  //   // {id:1 , discription:'learn to dance'},
  //   // {id:2, discription:'fun'},
  //   // {id:3 , discription:'run'},
  //   // {id:4 , discription:'eat'},
    
  // ]
  constructor(
    private todoService : TodoDataService,
    private router : Router 
  ) { }

  ngOnInit(): void {
    this.refreshTodo();
  }
  refreshTodo(){
    this.todoService.retrieveAllTodos('ssfoods').subscribe(
      response => {
        console.log(response);
        this.todos = response;
      }
    )
  }

  deleteTodo(id) {
    console.log(`delete todo ${id}`)
    this.refreshTodo();
    this.message = `Delete of Todo ${id} Sucessful`;
    this.todoService.deleteTodo('ssfoods',id).subscribe(
      response => {
         console.log(response);
         //
         

         
      }
    )  
  }
  updateTodo(id) {
   console.log(`update ${id}`); 
   this.router.navigate(['todos',id]);
  }

addTodo() {
  this.router.navigate(['todos',-1]);

}

}
