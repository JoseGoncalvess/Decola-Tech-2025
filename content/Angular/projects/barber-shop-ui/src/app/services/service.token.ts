import { InjectionToken } from "@angular/core";
import { IclientService } from "./api-client/clients/iclients.service";

export const  SERVICE_TOKEN = {
  HTTP: {
    CLIENT: new InjectionToken<IclientService>("SERVICE_TOKEN.HTTP.CLIENT")
    // SCHEDULE: new InjectionToken<IclientService>("SERVICE_TOKEN.HTTP.SCHEDULE")
  }
}
