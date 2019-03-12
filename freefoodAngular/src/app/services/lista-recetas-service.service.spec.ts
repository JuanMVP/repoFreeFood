import { TestBed, inject } from '@angular/core/testing';

import { ListaRecetasServiceService } from './lista-recetas-service.service';

describe('ListaRecetasServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ListaRecetasServiceService]
    });
  });

  it('should be created', inject([ListaRecetasServiceService], (service: ListaRecetasServiceService) => {
    expect(service).toBeTruthy();
  }));
});
