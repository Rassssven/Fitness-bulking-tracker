import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {

  const token = localStorage.getItem('token');

  if (token) {
    return true;
  } else {
    window.alert('You must be logged in to access this page.');
    return false;
  }

};
