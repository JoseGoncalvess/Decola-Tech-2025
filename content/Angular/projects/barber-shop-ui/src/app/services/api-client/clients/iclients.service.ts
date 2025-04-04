import { Observable } from "rxjs";
import { DetailClientResponse, ListClientResponse, SaveClientRequest, SaveClientResponse, UpdateClientResponse,   } from "./client.models";

export interface IclientService{
save(request: SaveClientRequest): Observable<SaveClientResponse>;
update(id: number, request: SaveClientRequest): Observable<UpdateClientResponse>;
delete ( id: number, request: SaveClientRequest): Observable<void>;
list ( ): Observable<ListClientResponse[]>;
findById(id:number): Observable<DetailClientResponse>;
}
