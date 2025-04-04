import { Component, Inject } from '@angular/core';
import { IclientService } from '../../services/api-client/clients/iclients.service';
import { SERVICE_TOKEN } from '../../services/service.token';
import { ClientsService } from '../../services/api-client/clients/clients.service';

@Component({
  selector: 'app-new-client',
  imports: [],
  templateUrl: './new-client.component.html',
  styleUrl: './new-client.component.scss',

  providers:[{
    provide:SERVICE_TOKEN.HTTP.CLIENT, useClass: ClientsService

  }]
})
export class NewClientComponent {
/**
 *
 */
constructor (@Inject(SERVICE_TOKEN.HTTP.CLIENT) private readonly: IclientService) {

}
}
