import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  focus: boolean = false;

  constructor() { }

  ngOnInit(): void {}

  focusOrBlur(focus: boolean): void {
    this.focus = focus
  }
    
}
