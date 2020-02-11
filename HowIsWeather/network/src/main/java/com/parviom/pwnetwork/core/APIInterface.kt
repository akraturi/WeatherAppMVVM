package com.parviom.pwnetwork.core

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {

    @GET
    fun get(@Url endPoint:String,
            @QueryMap(encoded = true) queryParams:Map<String, @JvmSuppressWildcards Any>? = null) : Call<JsonObject>

    @POST
    fun post(@Url endPoint:String,
             @Body payload:Map<String,@JvmSuppressWildcards Any>? = null) : Call<JsonObject>

    @PUT
    fun put(@Url endPoint:String,
            @Body payload:Map<String,@JvmSuppressWildcards Any>? = null) : Call<JsonObject>

    @DELETE
    fun delete(@Url endPoint:String) : Call<JsonObject>

/*    @Multipart
    @POST
    fun multipart(@Url endPoint:String,
                  @PartMap() Map<String, RequestBody> partMap,
                  @Part MultipartBody.Part file) : Call<JsonObject>*/

    @Multipart
    @POST
    fun multipart(
        @Url endPoint: String,
        @PartMap partMap: Map<String, Any?>?,
        @Part file: MultipartBody.Part?): Call<JsonObject>

}