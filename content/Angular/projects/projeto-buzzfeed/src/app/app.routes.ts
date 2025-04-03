import { Routes } from '@angular/router';
import { QuizzComponent } from './components/quizz/quizz.component';

export const routes: Routes = [
  { path: '', component: QuizzComponent, pathMatch:"full" }
];
