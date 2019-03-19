import { RestauranteResponse } from "./RestauranteResponse.interface";

export interface RestaurantContainer{
    count: number;
    rows: RestauranteResponse[];
}