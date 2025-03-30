import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';


export const authGuard: CanActivateFn = (
  route:ActivatedRouteSnapshot,
  state:RouterStateSnapshot) => {
    const hastAuth: boolean = !!localStorage.getItem('token')
  return hastAuth;
};
