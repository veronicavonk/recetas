package com.domain.veronica.proyectoveronicaapi.model;

/**
 * Created by Veronica on 20/09/2017.
 */

public class RespuestaReceta {

    public Detalle getRecipe() {
        return recipe;
    }

    public void setRecipe(Detalle recipe) {
        this.recipe = recipe;
    }

    private Detalle recipe;
}
