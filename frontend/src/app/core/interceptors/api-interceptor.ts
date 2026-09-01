import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';

const BASE_URL = 'http://localhost:8080/api/';

export const apiInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(Auth);
  const accessToken = auth.getAccessToken();

  let apiReq = req.clone({
    url: `${BASE_URL}${req.url}`,
  });

  if (accessToken !== null) {
    apiReq = apiReq.clone({
      setHeaders: {
        Authorization: `Bearer ${accessToken}`,
      },
    });
  }

  return next(apiReq);
};
