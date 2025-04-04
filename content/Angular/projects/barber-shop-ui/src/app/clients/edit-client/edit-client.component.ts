import { Component, Inject } from '@angular/core';
import { SERVICE_TOKEN } from '../../services/service.token';
import { IclientService } from '../../services/api-client/clients/iclients.service';
import { ClientsService } from '../../services/api-client/clients/clients.service';

@Component({
  selector: 'app-edit-client',
  imports: [],
  templateUrl: './edit-client.component.html',
  styleUrl: './edit-client.component.scss',
  providers:[{
        provide:SERVICE_TOKEN.HTTP.CLIENT, useClass: ClientsService
      }]
})
export class EditClientComponent {
  constructor (@Inject(SERVICE_TOKEN.HTTP.CLIENT) private readonly: IclientService) {

  }
}
