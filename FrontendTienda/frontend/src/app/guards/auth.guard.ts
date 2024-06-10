import { CanActivateFn, Router } from '@angular/router';
import { SessionStorageService } from '../services/session-storage.service';
import { inject } from '@angular/core';

//los guards son mecanismos que me permiten restringir urls específicas con un valor booleano
export const authGuard: CanActivateFn = (route, state) => {
  const session = inject(SessionStorageService);
  const router = inject(Router)//para hacer una redirección

  if (session.getItem('token') !== null) {
    return true;
  }

  return router.createUrlTree(['/user/login']);
};
