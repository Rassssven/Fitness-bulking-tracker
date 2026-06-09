import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Shop } from './Shop/shop/shop';
import { CalcPage } from './pages/calc-page/calc-page';
import { ResultPage } from './pages/result-page/result-page';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { ProfileDashboard } from './pages/profile-dashboard/profile-dashboard';
import { CustomizePlanPage } from './pages/customize-plan-page/customize-plan-page';
import { HelpPage } from './pages/help-page/help-page';
import { ProductCard } from './Shop/product-card/product-card';
import { DiagramPage } from './pages/diagram-page/diagram-page';
import { authGuard } from './core/guards/auth-guard';
import { AdminDash } from './pages/admin-dash/admin-dash';
import { FoodCatalog } from './pages/other_pages/food-catalog/food-catalog';
import { ExerciseCatalog } from './pages/other_pages/exercise-catalog/exercise-catalog';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'shop', component: Shop },
    { path: 'shop/:id', component: Shop },
    { path: 'calc-page', component: CalcPage },
    { path: 'result-page', component: ResultPage },
    { path: 'login', component: Login },
    { path: 'register', component: Register },
    { path: 'profile-dashboard', component: ProfileDashboard},
    { path: 'customize-plan-page/:id', component: CustomizePlanPage},
    { path: 'help', component: HelpPage, canActivate: [authGuard]},
    { path: 'product/:id', component: ProductCard},
    { path: 'login', component: Login},
    { path: 'register', component: Register},
    { path: 'diagram', component: DiagramPage },
    { path: 'admin', component: AdminDash },
    { path: 'food-catalog', component: FoodCatalog },
    { path: 'exercise-catalog', component: ExerciseCatalog }
];
