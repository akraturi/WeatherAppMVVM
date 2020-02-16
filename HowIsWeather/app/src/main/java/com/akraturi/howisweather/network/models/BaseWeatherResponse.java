package com.akraturi.howisweather.network.models;

// {
//         "dt": 1581832800,
//         "main": {
//         "temp": 294.73,
//         "feels_like": 292.41,
//         "temp_min": 294.73,
//         "temp_max": 294.73,
//         "pressure": 1019,
//         "sea_level": 1019,
//         "grnd_level": 900,
//         "humidity": 35,
//         "temp_kf": 0
//         },
//         "weather": [
//         {
//         "id": 800,
//         "main": "Clear",
//         "description": "clear sky",
//         "icon": "01d"
//         }
//         ],
//         "clouds": {
//         "all": 0
//         },
//         "wind": {
//         "speed": 1.84,
//         "deg": 243
//         },
//         "sys": {
//         "pod": "d"
//         },
//         "dt_txt": "2020-02-16 06:00:00"
//         }

import com.akraturi.howisweather.data.models.Weather;
import com.akraturi.howisweather.utils.AppUtils;
import com.akraturi.howisweather.utils.OpenWeatherMapApiUtils;
import managers.DataManager;
import utils.AppLogger;

import java.util.List;
import java.util.Map;

public class BaseWeatherResponse {

    private final String TAG = BaseWeatherResponse.class.getSimpleName();
    private Long dt;
    private WeatherInfo main;
    private List<Map<String,Object>> weather;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }


    public List<Map<String, Object>> getWeather() {
        return weather;
    }

    public void setWeather(List<Map<String, Object>> weather) {
        this.weather = weather;
    }

    Weather toWeather(String location){
        long status = (long) weather.get(0).get("id");

        AppLogger.Companion.logCurrentMethodName(TAG);
        AppLogger.Companion.debugLog(TAG,this.toString());

        return toWeather(location,AppUtils.INSTANCE.day(dt));
    }

    Weather toWeather(String location,String day){
        long status = (long) weather.get(0).get("id");

        AppLogger.Companion.logCurrentMethodName(TAG);
        AppLogger.Companion.debugLog(TAG,this.toString());

        return new Weather(
                location,
                (String)weather.get(0).get("description"),
                AppUtils.INSTANCE.toDegreeCelsius((Double)main.getTemp()),
                AppUtils.INSTANCE.toDegreeCelsius((Double)main.getTempMin()),
                AppUtils.INSTANCE.toDegreeCelsius((Double)main.getTempMax()),
                String.valueOf( main.getPressure()),
                String.valueOf(main.getHumidity()),
                day,
                AppUtils.INSTANCE.time(dt),
                OpenWeatherMapApiUtils.INSTANCE.getCondition(status)
        );
    }

    public WeatherInfo getMain() {
        return main;
    }

    public void setMain(WeatherInfo main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return  DataManager.getInstance().getGsonBuilder().toJson(this);
    }
}
