package com.android.practice.library.rest;

import com.android.practice.library.mode.Example;
import com.android.practice.library.mode.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestClient {

    /*
    http://samples.openweathermap.org/data/2.5/weather?lat=LATITUDE&lon=LONGITUDE&appid=APP_ID
     */
    @GET("weather")
    Call<ServerResponse<Example>> weatherByLatLong(@Query("lat") String latitude,
                                                   @Query("lon") String longitude,
                                                   @Query("appid") String apiKey);
}
