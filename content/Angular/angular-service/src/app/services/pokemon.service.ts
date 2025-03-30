import { Injectable } from '@angular/core';
import { environment } from "../../app/environments/environments";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { pokemonData } from '../models/pokemData';


@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  private baseURL : string = ""
  private pokemonData : pokemonData | any = ""

  constructor(
    private http:HttpClient
  ) {
    this.baseURL = environment.pokeapi;


  }

  getPokemon(pokemonName :string ) : Observable<pokemonData> {
    this.pokemonData =  this.http.get<pokemonData>(`${this.baseURL}${pokemonName}`)

    return this.pokemonData;
  }
}
