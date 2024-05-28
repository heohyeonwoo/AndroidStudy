package com.example.bdad9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.net.ResponseCache;

import retrofit2.Callback;
import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);

        Call<WeatherResponse> call = weatherService.getCurrentWeather("Seoul", "65446132ab165472e50e0c0e6c3b8725", "metric");

        call.enqueue(new Callback<WeatherResponse>()
        {
            @Override
            public void onResponse(Call <WeatherResponse> call, Response<WeatherResponse> response)
            {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        WeatherMain main = weatherResponse.getMain();
                        double temperature = main.getTemperature();
                        Toast.makeText(MainActivity.this, "Current temperature in Seoul: " + temperature + "°C", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure (Call <WeatherResponse> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Network failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}