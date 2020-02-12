package com.akraturi.howisweather.network.models

data class LocationQuery(
    var lat:Double,
    var lon:Double
): BaseQuery()