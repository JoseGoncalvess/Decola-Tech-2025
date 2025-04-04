import { Component, Inject } from '@angular/core';
import { SERVICE_TOKEN } from '../../services/service.token';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { IClientService } from '../../services/api-client/clients/iclients.service';

@Component({
  selector: 'app-list-clients',
  imports: [],
  templateUrl: './list-clients.component.html',
  styleUrl: './list-clients.component.scss',
    providers:[{
      provide:SERVICE_TOKEN.HTTP.CLIENT, useClass: ClientsService
    }]
})
export class ListClientsComponent {
  constructor (@Inject(SERVICE_TOKEN.HTTP.CLIENT) private readonly: IClientService) {

  }
}
