package com.domain.veronica.proyectoveronicaapi.model;

import java.util.ArrayList;

/**
 * Created by Veronica on 20/09/2017.
 */

public class DatosRespuesta {

    //esta clase tiene toda la respuesta

    private boolean success;
    private int count;

    public ArrayList<Comida> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Comida> recipes) {
        this.recipes = recipes;
    }

    private ArrayList<Comida> recipes;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


