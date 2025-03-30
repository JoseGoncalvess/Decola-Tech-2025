import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

// COM AS NOVAS ATUALIZAÇÂOES O HTTP PRECISADO PROVIDE
// SENDO ASSIM NECESARIO PASSAR POR AQUI
import { provideHttpClient } from '@angular/common/http'



import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
     provideClientHydration(withEventReplay()),provideHttpClient()]
};
