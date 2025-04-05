import { Component, Inject, OnDestroy } from '@angular/core';
import { IClientService } from '../../services/api-client/clients/iclients.service';
import { SERVICE_TOKEN } from '../../services/service.token';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { ClientFormComponent } from "../components/client-form/client-form.component";
import { Subscription } from 'rxjs';
import { ClientModelForm } from '../client.models';
import { Router } from '@angular/router';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';

@Component({
  selector: 'app-new-client',
  imports: [ClientFormComponent],
  templateUrl: './new-client.component.html',
  styleUrl: './new-client.component.scss',

  providers: [
    {provide: SERVICE_TOKEN.HTTP.CLIENT, useClass: ClientsService},
    { provide: SERVICE_TOKEN.SNACKBAR, useClass: SnackbarManagerService }]
})
export class NewClientComponent implements OnDestroy {
  private httpSubscription?: Subscription


  constructor(
    private router: Router,
    @Inject(SERVICE_TOKEN.HTTP.CLIENT) private readonly httpService: IClientService,
    @Inject(SERVICE_TOKEN.SNACKBAR) private readonly snackBarManager: ISnackbarManagerService,) {

  }


  ngOnDestroy(): void {
    if (this.httpSubscription) {
      this.httpSubscription.unsubscribe()
    }
  }

  onSubmitClient(value: ClientModelForm) {
    const { id, ...request } = value
    this.httpSubscription = this.httpService.save(request).subscribe(_ => {
      this.snackBarManager.show('Usuário cadastrado com sucesso')
      this.router.navigate(['clients/list'])
    })
  }


}
