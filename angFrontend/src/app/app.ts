import { Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import { Header } from "./pages/header/header";
import { Footer } from "./pages/footer/footer";
import { Home } from "./pages/home/home";
import { RouterOutlet } from "@angular/router";

import { GridModule } from '@progress/kendo-angular-grid';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { Notification } from "./shared/notification/notification";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    Header,
    Footer,
    Home,
    RouterOutlet,
    GridModule,
    ButtonsModule,
    Notification
],
  templateUrl: './app.html'
})
export class AppComponent {

}
