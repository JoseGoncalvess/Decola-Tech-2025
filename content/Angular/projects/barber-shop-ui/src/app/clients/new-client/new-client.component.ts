import { Component, Inject } from '@angular/core';
import { ICLientService } from '../../services/api-client/clients/iclients.service';
import { SERVICE_TOKEN } from '../../services/service.token';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { ClientFormComponent } from "../components/client-form/client-form.component";
import { Subscription } from 'rxjs';
import { ClientModelForm } from '../client.models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-client',
  imports: [ClientFormComponent],
  templateUrl: './new-client.component.html',
  styleUrl: './new-client.component.scss',

  providers: [{
    provide: SERVICE_TOKEN.HTTP.CLIENT, useClass: ClientsService

  }]
})
export class NewClientComponent {
  private httpSubscription?: Subscription


  constructor(
    private router: Router,
    @Inject(SERVICE_TOKEN.HTTP.CLIENT) private readonly httpService: ICLientService,) {

  }


  ngOnDestroy(): void {
    if (this.httpSubscription) {
      this.httpSubscription.unsubscribe()
    }
  }

  onSubmitClient(value: ClientModelForm) {
    const { id, ...request } = value
    this.httpSubscription = this.httpService.save(request).subscribe(_ => {
      this.router.navigate(['clients/list'])
    })

  }
}
