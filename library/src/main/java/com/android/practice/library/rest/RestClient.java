package com.android.practice.library.rest;

import com.android.practice.library.model.Example;
import com.android.practice.library.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestClient {

    /*
    http://samples.openweathermap.org/data/2.5/weather?lat=LATITUDE&lon=LONGITUDE&appid=APP_ID
     */
    @GET("weather")
    Call<Example> getAllPost(@Query("lat") String latitude,
                             @Query("lon") String longitude,
                             @Query("appid") String apiKey);

    /*
    * https://jsonplaceholder.typicode.com/posts
    * */
    @GET("posts")
    Call<List<Posts>> getAllPost();

    /*
    * https://jsonplaceholder.typicode.com/posts/1
    * */
    @GET("posts/{id}")
    Call<List<Posts>> getSpecificPost(@Path("id") String id);

    /*
    * https://jsonplaceholder.typicode.com/photos
    * */
    @GET("photos")
    Call<List<Posts>> getAllPhotos();
}
