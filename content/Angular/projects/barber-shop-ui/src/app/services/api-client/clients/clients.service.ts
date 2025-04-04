import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IclientService } from './iclients.service';
import { Observable } from 'rxjs';
import { environment } from '../../../../environment/environment';
import { SaveClientRequest, SaveClientResponse, UpdateClientResponse, ListClientResponse, DetailClientResponse } from './client.models';

@Injectable({
  providedIn: 'root'
})
export class ClientsService implements IclientService {

  private baseUrl = environment.apiUrl

  constructor( private http: HttpClient) { }
  save(request: SaveClientRequest): Observable<SaveClientResponse> {
    return this.http.post<SaveClientResponse>(`${this.baseUrl}client`, request)
  }
  update(id: number, request: SaveClientRequest): Observable<UpdateClientResponse> {
    return this.http.put<SaveClientResponse>(`${this.baseUrl}client/${id}`, request)

  }
  delete(id: number, request: SaveClientRequest): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}client/${id}`)

  }
  list(): Observable<ListClientResponse[]> {
    return this.http.get<ListClientResponse[]>(`${this.baseUrl}client`,)

  }
  findById(id: number): Observable<DetailClientResponse> {
    return this.http.get<DetailClientResponse>(`${this.baseUrl}client/${id}`)

  }
}
