package com.akraturi.howisweather.network.models

import com.akraturi.howisweather.data.models.Weather

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
data class CurrentWeatherResponse(
    val name:String
): BaseWeatherResponse(){

    fun toWeather():Weather{
        return super.toWeather(name)
    }
}