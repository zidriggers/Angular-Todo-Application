import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  errMessage = 'Error has occured! Please contact support.'
  constructor() { }

  ngOnInit(): void {
  }

}
