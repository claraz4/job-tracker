import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';

const BASE_URL = 'http://localhost:8080/api/';

export const apiInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(Auth);
  const userId = auth.getUserId();

  if (userId === null) {
    const apiReq = req.clone({
      url: `${BASE_URL}${req.url}`,
    });

    return next(apiReq);
  }

  const apiReq = req.clone({
    url: `${BASE_URL}users/${userId}/${req.url}`,
  });

  return next(apiReq);
};
