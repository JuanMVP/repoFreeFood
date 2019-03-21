package com.example.freefoodapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.freefoodapp.models.Recipe;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<String> idRecipe = new MutableLiveData<>();
    private final MutableLiveData<List<Recipe>> recetas = new MutableLiveData<>();

    public void selectIdRecipe(String id) { idRecipe.setValue(id);}

    public void selectRecipeList(List<Recipe> recipeList) {
        recetas.setValue(recipeList);
    }

    public MutableLiveData<String> getSelectedIdInmuble() {
        return idRecipe;
    }


    public MutableLiveData<List<Recipe>> getAll() { return recetas; }
}
