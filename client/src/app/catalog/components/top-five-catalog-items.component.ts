import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CatalogItem } from '../models';
import { BasketItem } from '../../models';

@Component({
  selector: 'es-top-five-catalog-items',
  templateUrl: './top-five-catalog-items.component.html',
  styleUrls: ['./top-five-catalog-items.component.css']
})
export class TopFiveCatalogItemsComponent {
  @Input() catalogItems: CatalogItem[];
  @Output() addItemToCart = new EventEmitter<BasketItem>();
}
