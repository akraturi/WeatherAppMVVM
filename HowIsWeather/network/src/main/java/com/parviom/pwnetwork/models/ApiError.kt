package com.parviom.pwnetwork.models

import managers.DataManager

data class ApiError(
    val message:String,
    val code:Int
){
    override fun toString(): String {
        return DataManager.instance.gsonBuilder.toJson(this)
    }

    fun isNull():Boolean{
        return this.message.isNullOrBlank() && code == 0
    }

    fun isNotNull():Boolean{
        return !isNull()
    }
}