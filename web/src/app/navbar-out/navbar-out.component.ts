import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../Service/service.service';

@Component({
  selector: 'app-navbar-out',
  templateUrl: './navbar-out.component.html',
  styleUrls: ['./navbar-out.component.css']
})
export class NavbarOutComponent implements OnInit {
  userLogged = this.authService.getUserLogged();
  disabled: boolean = false;

  constructor(private authService: ServiceService, private route: Router) { }

  ngOnInit(): void {
    this.traerdatos();
  }

  traerdatos() {
    this.userLogged.subscribe((value) => {
      if (value?.email == undefined) {
        this.disabled = true;
      } else {
        this.disabled = false;
      }
    });
  }

  logout() {
    this.route.navigate(['login']);
  }
}
