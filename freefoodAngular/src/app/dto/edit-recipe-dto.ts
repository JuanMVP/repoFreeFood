export class EditRecipeDto{
    id: String;
    name: String;
    description: String;
    ingredients: String;
    dinnerGuest: Number;
    //photoRecipes: String[];

    constructor(name: String, description: String, ingredients: String, dinnerGuest: Number){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.dinnerGuest = dinnerGuest;
    }
}