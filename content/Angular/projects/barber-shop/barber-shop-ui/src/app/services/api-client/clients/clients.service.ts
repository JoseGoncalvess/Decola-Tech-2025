import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IClientService } from './iclients.service';
import { Observable } from 'rxjs';
import { environment } from '../../../../environment/environment';
import { DetailClientResponse, ListClientResponse, SaveClientRequest, SaveClientResponse, UpdateClientRequest, UpdateClientResponse } from './client.models';

@Injectable({
  providedIn: 'root'
})
export class ClientsService implements IClientService {

  private baseUrl = environment.apiUrl

  constructor( private http: HttpClient) { }
  save(request: SaveClientRequest): Observable<SaveClientResponse> {
    return this.http.post<SaveClientResponse>(`${this.baseUrl}clients`, request)
  }
  update(id: number, request: UpdateClientRequest): Observable<UpdateClientResponse> {
    return this.http.put<UpdateClientResponse>(`${this.baseUrl}clients/${id}`, request)
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}clients/${id}`)
  }
  list(): Observable<ListClientResponse[]> {
    return this.http.get<ListClientResponse[]>(`${this.baseUrl}clients`)
  }
  findById(id: number): Observable<DetailClientResponse> {
    return this.http.get<DetailClientResponse>(`${this.baseUrl}clients/${id}`)
  }
}
