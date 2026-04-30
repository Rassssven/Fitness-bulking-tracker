import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Shop } from './pages/shop/shop';
import { CalcPage } from './pages/calc-page/calc-page';
import { ResultPage } from './pages/result-page/result-page';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'shop', component: Shop },
    { path: 'calc-page', component: CalcPage },
    { path: 'result-page', component: ResultPage }
];
