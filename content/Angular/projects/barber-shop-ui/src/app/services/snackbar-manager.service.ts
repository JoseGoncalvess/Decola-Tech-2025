
import { ISnackbarManagerService } from './isnackbar-manager.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SnackbarManagerService implements ISnackbarManagerService {

  constructor(private readonly snackBar: MatSnackBar) { }

  show(message: string, action: string = "FECHAR", duration: number = 2000): void {
    this.snackBar.open(message, action, { duration, verticalPosition: 'top', horizontalPosition: 'right' })
  }
}
