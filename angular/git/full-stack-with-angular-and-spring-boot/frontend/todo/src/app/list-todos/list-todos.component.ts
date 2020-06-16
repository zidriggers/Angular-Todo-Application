import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Router } from '@angular/router';

export class Todo {
  constructor(
    public id: number,
    public description: string,
    public done: boolean, 
    public targetDate: Date
  ){

  }
}

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})

export class ListTodosComponent implements OnInit {

  todos : Todo[]

  message: string

  constructor(
    private todoService:TodoDataService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshTodos();
  }
  
  refreshTodos()
  {
    this.todoService.retrieveAllToDos('validuser').subscribe(
      response => {
        console.log(response);
        this.todos = response;
      }
    )
  }

  deleteTodo(id){
    console.log(`delete todo ${id}`);
    this.todoService.deleteTodo('validuser', id).subscribe(
      response => {
        console.log(response);
        this.message= `Delete of Todo ${id} Successful`;
        this.refreshTodos();
      }
    )
  }

  updateTodo(id){
    console.log(`update todo ${id}`)
    this.router.navigate(['todos',id])
  }

  addTodo(){
    this.router.navigate(['todos',-1])
  }
}
