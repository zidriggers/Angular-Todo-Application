import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Todo } from '../../list-todos/list-todos.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllToDos(userName){
    return this.http.get<Todo[]>(`http://localhost:8080/user/${userName}/todos`);
  }

  deleteTodo(userName, id){
    return this.http.delete(`http://localhost:8080/user/${userName}/todos/${id}`);
  }

  retrieveTodo(userName, id){
    return this.http.get<Todo>(`http://localhost:8080/user/${userName}/todos/${id}`);
  }

  updateTodo(userName, id, todo){
    return this.http.put(`http://localhost:8080/user/${userName}/todos/${id}`, todo);
  }

  createTodo(userName, todo){
    return this.http.post(`http://localhost:8080/user/${userName}/todos`, todo);
  }

}
