package com.example.nearestbarsapp.retrofit;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.nearestbarsapp.R;
import com.example.nearestbarsapp.models.BarCollection;
import com.example.nearestbarsapp.models.BarModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static NetworkManager instance;
    private RetrofitMaps service;
    private Resources resources;

    public static NetworkManager getInstance() {
        if(instance == null) instance = new NetworkManager();
        return instance;
    }

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitMaps.class);
    }

    public void getNearbyBars(double latitude, double longitude, int radius, final DataListener<List<BarModel>> dataListener) {
        Call<BarCollection> call = service.getNearbyPlaces("bar", latitude + "," + longitude, radius);
        call.enqueue(new Callback<BarCollection>() {
            @Override
            public void onResponse(@NonNull Call<BarCollection> call, @NonNull Response<BarCollection> response) {
                if(response.isSuccessful() && response.body() != null) {
                    BarCollection bars = response.body();
                    dataListener.onData(bars.getResults());
                } else {
                    dataListener.onError(resources.getString(R.string.no_bars_found));
                }
            }

            @Override
            public void onFailure(@NonNull Call<BarCollection> call, @NonNull Throwable t) {
                dataListener.onError(t.getLocalizedMessage());
            }
        });
    }

    public interface DataListener<T> {
        void onData(T data);
        void onError(String error);
    }

}
