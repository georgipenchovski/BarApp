package com.example.nearestbarsapp.retrofit;

import com.example.nearestbarsapp.POJO.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitMaps {

    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyAgyZuAj_jhJTZa5-7umraXyz1sHRlhNXk")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}
