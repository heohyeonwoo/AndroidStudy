package com.example.bdad9;

import com.google.gson.annotations.SerializedName;



public class WeatherResponse {

    @SerializedName("main")
    private WeatherMain main;

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }
}

class WeatherMain {
    @SerializedName("temp")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}


