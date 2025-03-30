import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'firstUpcase'
})
export class FirstUpcasePipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): string {
    return value[0].toUpperCase() + value.substring(1)
  }

}
