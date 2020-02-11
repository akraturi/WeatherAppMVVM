package com.parviom.pwnetwork.utils

import com.google.gson.JsonObject
import com.parviom.pwnetwork.core.APIResponseHandler
import com.parviom.pwnetwork.models.ApiError
import managers.DataManager
import okhttp3.ResponseBody
import org.json.JSONObject
import org.xml.sax.ErrorHandler
import utils.AppLogger
import java.lang.Exception

class APIManagerUtils {

    companion object{
        fun getJSONObject(json : String?):JSONObject{

            return if(json!=null) {
                JSONObject(json)
            }else{
                JSONObject("")
            }
        }

        fun <T> getMap(obj: T?): Map<String,Any> {
            AppLogger.logCurrentMethodName("getMap")
            return DataManager.instance.getMap(obj)
        }

        fun <T> parseJson(jsonObject: JsonObject?,type:Class<T>): T? {
            try {
                return parseJson(jsonObject.toString(), type)
            }catch(e:Exception){
                e.printStackTrace()
            }
            return null
        }

        fun <T> parseJson(json: String,type:Class<T>): T?{
            try {
                return DataManager.instance.gsonBuilder.fromJson(json, type)
            }catch(e:Exception){
                e.printStackTrace()
            }
            return null
        }

        fun parseError(errorBody:ResponseBody):ApiError?{
           return parseJson(errorBody.string(),ApiError::class.java)
        }

        fun parseError(error:String):ApiError?{
            return parseJson(error,ApiError::class.java)
        }

        fun toJson(src:Any):String{
            try {
                return DataManager.instance.gsonBuilder.toJson(src)
            }catch (e:Exception){
                e.printStackTrace()
            }
            return ""
        }

    }
}