import { Injectable } from '@angular/core';

export interface BadgeItem {
  type: string;
  value: string;
}

export interface ChildrenItems {
  state: string;
  name: string;
  type?: string;
}

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  badge?: BadgeItem[];
  children?: ChildrenItems[];
}

const MENUITEMS = [
  {
    state: 'home',
    name: 'Recetas',
    type: 'link',
    icon: 'restaurant'
  },
  {
    state: 'usuarios',
    name: 'Usuarios',
    type: 'link',
    icon: 'person'
  },
  {
    state: 'restaurantes',
    name: 'Restaurantes',
    type: 'link',
    icon: 'restaurant'

  },
  
];

@Injectable()
export class MenuService {
  getAll(): Menu[] {
    return MENUITEMS;
  }

  add(menu) {
    MENUITEMS.push(menu);
  }
}
