package com.parviom.pwnetwork.core

import com.google.gson.JsonObject
import com.parviom.pwnetwork.utils.APIResponseCodes
import com.parviom.pwnetwork.utils.APIManagerUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.AppLogger
import java.io.IOException

abstract class ApiCallback<T>(private val responseType: Class<T>) : Callback<JsonObject> {

    private val TAG = ApiCallback::class.java.simpleName

    abstract fun onSuccess(responseObject: T)
    abstract fun onFailure(errorMessage: String, status: Int)

    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
        AppLogger.logCurrentMethodName(TAG)

//        AppLogger.infoLog(TAG,"Complete Response:- ${APIManagerUtils.toJson(response)}")
        AppLogger.infoLog(TAG, "Response code:- ${response.code()}")
        AppLogger.infoLog(TAG, "Response message:- ${response.message()}")


        if (response.isSuccessful) {

            AppLogger.infoLog(TAG, "Successful api response")

            if (response.body() != null) {

                AppLogger.infoLog(TAG, "Response body is not null")
                AppLogger.infoLog(TAG, "Response body:- ${response.body()}")

                val obj = APIManagerUtils.parseJson(response.body(), responseType)

                if(obj!=null){
                    onSuccess(obj)
                }else{
                    forwardError(response)
                }

            } else {
                AppLogger.errorLog(TAG, "Response body is null")

                forwardError(response)
            }

        } else {

            AppLogger.errorLog(TAG, "Failure api response")

            forwardError(response)
        }
    }

    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

        AppLogger.logCurrentMethodName(TAG)
        AppLogger.errorLog(TAG, "API request failed")
//        AppLogger.errorLog(TAG,"Failed call:- ${APIManagerUtils.toJson(call)}")
//        AppLogger.errorLog(TAG,"Thrown object: - ${APIManagerUtils.toJson(t)}")
        AppLogger.errorLog(TAG,"Thrown error message:- ${t.message}")

        t.printStackTrace()

        if (t is IOException) {

            // Internet connection issue
            AppLogger.errorLog(TAG, "Failed to connect to api")

            onFailure(
                APIResponseHandler.getMessage(
                    APIResponseCodes.API_CONNECTION_ERROR
                ),
                APIResponseCodes.API_CONNECTION_ERROR
            )

        } else {

            // Response parsing issue
            AppLogger.errorLog(TAG, "Failed to parse request body")

            onFailure(
                APIResponseHandler.getMessage(
                    APIResponseCodes.REQUEST_PARSING_ERROR
                ),
                APIResponseCodes.REQUEST_PARSING_ERROR
            )

        }
    }

    private fun forwardError(response:Response<JsonObject>){

        AppLogger.logCurrentMethodName(TAG)

//        val errorBody:ResponseBody? = response.errorBody()
//
//        if(errorBody!=null){
//
//            val error = APIManagerUtils.parseError(errorBody)
//            AppLogger.debugLog(TAG,"Error after parsing:${error}")
//
//            if(error!=null){
//                AppLogger.debugLog(TAG,"Parsed error is not null")
//                onFailure(error.message,error.code)
//            }else {
//                AppLogger.debugLog(TAG,"Parsed error is null")
//                AppLogger.debugLog(TAG,"Forwarding errors hardcoded on app side")
//                onFailure(
//                    APIResponseHandler.getMessage(
//                        response.code()
//                    ),
//                    response.code()
//                )
//            }
//        }else{
//            AppLogger.debugLog(TAG,"Error body is null")
//            onFailure(
//                APIResponseHandler.getMessage(
//                    response.code()
//                ),
//                response.code()
//            )
//        }

        onFailure(
                APIResponseHandler.getMessage(
                    response.code()
                ),
                response.code()
            )
    }

}