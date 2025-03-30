import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pokeName'
})
export class PokeNamePipe implements PipeTransform {

  transform(value: string, pokeId: number): string {
    return `${pokeId} - ${value.toUpperCase()}`;
  }

}
