import { Component, OnInit, } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PokemonService } from "../../services/pokemon.service";
import { pokemonData } from '../../models/pokemData';
import { FormsModule } from "@angular/forms";
import { FirstUpcasePipe } from "../../pipes/first-upcase.pipe";
import { PokeNamePipe } from "../../pipes/poke-name.pipe";

@Component({
  selector: 'app-card',
  imports: [CommonModule, FormsModule, FirstUpcasePipe, PokeNamePipe],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent implements OnInit {
  pokemon?: pokemonData
  pokeImg: String = ""

  constructor(private pokemonService: PokemonService) {
    this.pokemon = {
      name: "",
      id: 0,
      sprites: {
        front_default: "",
        other: {
          "official-artwork": {
            front_default: ""
          }
        }
      },
      types: []
    }

  }
  ngOnInit(): void {
    this.getPokemon("onix");
  }

  getPokemon(searchPokemon: string) {
    if (searchPokemon != "" && searchPokemon != this.pokemon?.name) {
      this.pokemonService.getPokemon(searchPokemon).subscribe(
        {
          next: (ress) => {
            this.pokemon = {
              name: ress.name,
              id: ress.id,
              sprites: ress.sprites,
              types: ress.types
            }

            this.pokeImg = ress.sprites.other['official-artwork'].front_default

          },
          error: (ress) => console.log(ress),
        }
      )
    }

  }

  getBackgroundColor(type: string): string {
    switch (type.toLowerCase()) {
      case 'fire':
        return 'red';
      case 'water':
        return 'blue';
      case 'grass':
        return 'green';
      case 'ground':
        return "#a87f41"
      case 'poison':
        return "purple"
      case "rock":
        return "gray"
      case "fighting":
        return "#7c400f"

      case "electric":
        return "#ffd000"

        case "ice":
        return "#00b7ff"

      case "psychic":
        return "pink"
      default:
        return 'gray';
    }
  }



}
