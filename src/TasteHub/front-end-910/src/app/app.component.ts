import { AfterViewInit, Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../service/security/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  constructor(private authService :AuthService) {
  }


  isUserLogin(): boolean {
    return this.authService.isUserLogin();
  }

  ngAfterViewInit(): void {
    // main.js runs before Angular creates this element, so its startup timer
    // cannot hide the spinner. Hide it after the root view has been rendered.
    setTimeout(() => {
      document.getElementById('spinner')?.classList.remove('show');
    });
  }
}
