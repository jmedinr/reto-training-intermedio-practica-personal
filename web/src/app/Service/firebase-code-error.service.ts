import { Injectable } from '@angular/core';
import { FirebaseCodeErrorEnum } from '../utils/firebase-code-error';

@Injectable({
  providedIn: 'root',
})
export class FirebaseCodeErrorService {
  constructor() {}

  codeError(code: string) {
    switch (code) {
      // El correo ya existe
      case FirebaseCodeErrorEnum.EmailAlreadyInUse:
        return 'El usuario ya existe';

      // Contraseña debil
      case FirebaseCodeErrorEnum.WeakPassword:
        return 'La contraseña es muy debil';

      //Email invalido
      case FirebaseCodeErrorEnum.InvalidEmail:
        return 'Correo invalido';

      // La contraseña es incorrecta
      case FirebaseCodeErrorEnum.WrongPassword:
        return 'Contraseña incorrecta';

      //El correo no es correcto
      case FirebaseCodeErrorEnum.UserNotFound:
        return 'Email no esta registrado';

      default:
        return 'Error desconocido';
    }
  }
}
