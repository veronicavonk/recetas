package com.domain.veronica.proyectoveronicaapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.domain.veronica.proyectoveronicaapi.adapter.ComidaAdapter;
import com.domain.veronica.proyectoveronicaapi.model.Comida;
import com.domain.veronica.proyectoveronicaapi.model.DatosRespuesta;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Veronica on 19/09/2017.
 */

public class ComidasActivity extends AppCompatActivity implements ComidaAdapter.onComidaSelectedListener{

    private static final Gson gson = new Gson();

    private RecyclerView comidasRecyclerView;
    private ComidaAdapter comidasAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_comidas);

        button=(Button)findViewById(R.id.button);

        comidasRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView_comidas);
        comidasRecyclerView.setHasFixedSize(true);
        comidasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        comidasAdapter = new ComidaAdapter(this, this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        comidasRecyclerView.setLayoutManager(layoutManager);

        comidasRecyclerView.setAdapter(comidasAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        swipeRefreshLayout.setRefreshing(true);
        cargarDatos();
    }

    private void cargarDatos() {

        // TODO: Hacer el request
        //clase 05_09_17
        //paso 1. agarrar la referencia del servicio   createService(el nombre de la clase)
        DatosF2FService service = ServiceGenerator.createService(DatosF2FService.class);
        //se ecribe la llamada
        //en los parentesis debe mandar el APIKEY
        //y luego se guarda la respuesta en DatosResponse


        Call<DatosRespuesta> call= service.establecimientosEducativos("7b790470dffb036404df84755ffa8405");

        // en los parentesis van los codigos de respuestas. y se genera



        call.enqueue(new Callback<DatosRespuesta>() {

            @Override
            public void onResponse(Call<DatosRespuesta> call, Response<DatosRespuesta> response) {
                Log.d("este es el --response", new Gson().toJson(response.body()));
                swipeRefreshLayout.setRefreshing(false);
                //aqui viene si la peticion fue exitosa
                if(response.isSuccessful()){
                    //como muestro los datos? tengo que aceder a mi informacion
                    //el getResult es de la clase y ya tenemos los datos del json
                    //entonces ya tenemos la lista de comidas
                    ArrayList<Comida> listita = response.body().getRecipes();
                    comidasAdapter.setDataset(listita);
                }else{
                    Toast.makeText(ComidasActivity.this, "algo salio muy mal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DatosRespuesta> call, Throwable t) {
                //aqui viene si la peticion fue ...

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    /**
     * Este método se ejecuta cuando se presiona una Unidad Educativa
     */
    //@Override
    public void onComidaSelected(Comida departamento) {
//        Intent intent = new Intent(this, Comi.class);
//        intent.putExtra("depto", gson.toJson(departamento));
//        startActivity(intent);
    }

    @Override
    public void onComidaSelectedListener(Comida miinterface) {
        //Toast.makeText(this, "nhniniuni  "+gson.toJson(miinterface)+" "+miinterface.getRecipeId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetalleAtivity.class);
        intent.putExtra("comidita", gson.toJson(miinterface));
        startActivity(intent);

    }
}
