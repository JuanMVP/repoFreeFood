import { Routes } from '@angular/router';

import { NotFoundComponent } from './not-found/not-found.component';
import { ErrorComponent } from './error/error.component';
import { ForgotComponent } from './forgot/forgot.component';
import { LockscreenComponent } from './lockscreen/lockscreen.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';

export const SessionRoutes: Routes = [
  {
    path: '',
    children: [
      //{ path: '', redirectTo: '/session/login' },
      {
        path: '',
        component: SigninComponent
      }
    ]
  }
];
