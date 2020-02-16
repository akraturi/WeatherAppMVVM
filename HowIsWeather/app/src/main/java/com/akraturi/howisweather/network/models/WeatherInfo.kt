package com.akraturi.howisweather.network.models

//{
    //         "temp": 294.73,
//         "feels_like": 292.41,
//         "temp_min": 294.73,
//         "temp_max": 294.73,
//         "pressure": 1019,
//         "sea_level": 1019,
//         "grnd_level": 900,
//         "humidity": 35,
//         "temp_kf": 0
//         }
data class WeatherInfo(
     val temp:Double,
     val feelsLike:Double,
     val tempMin:Double,
     val tempMax:Double,
     val pressure:Long,
     val seaLevel:Long,
     val grndLevel:Long,
     val humidity:Long
)