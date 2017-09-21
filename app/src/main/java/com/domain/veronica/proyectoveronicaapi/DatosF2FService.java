package com.domain.veronica.proyectoveronicaapi;

import com.domain.veronica.proyectoveronicaapi.model.DatosRespuesta;
import com.domain.veronica.proyectoveronicaapi.model.Detalle;
import com.domain.veronica.proyectoveronicaapi.model.RespuestaReceta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Veronica on 20/09/2017.
 */

public interface DatosF2FService {

    //aqui van todas las peticiones.
//    @GET("action/datastore_search")
//    Call<DatosRespuesta> establecimientosEducativos(@Query("resource_id") String resourceId, @Query("limit") int limit);

    @GET("search")
    Call<DatosRespuesta> establecimientosEducativos(@Query("key") String key);

    @GET("get")
    Call<RespuestaReceta> establecimientosEducativos(@Query("key") String key, @Query("rId") String rId);


}
