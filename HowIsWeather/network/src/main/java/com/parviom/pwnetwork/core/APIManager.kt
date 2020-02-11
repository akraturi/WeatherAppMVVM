package com.parviom.pwnetwork.core

import com.parviom.pwnetwork.utils.APIManagerUtils
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import utils.AppLogger


class APIManager private constructor(baseUrl: String, header: Map<String, String>) {

    val TAG = APIManager::class.java.simpleName
    private var apiInterface: APIInterface? = null
    private var BASE_URL: String? = baseUrl

    companion object {

        fun getInstance(baseUrl: String, header: Map<String, String>): APIManager {
            return APIManager(baseUrl, header)
        }
    }

    init {
        val retrofit: Retrofit? =
            RetrofitClient.getInstance(
                baseUrl,
                header
            )
        apiInterface = retrofit?.create(APIInterface::class.java)
    }


    fun <T, V> get(url: String, query: T? = null, callback: ApiCallback<V>) {

        AppLogger.infoLog(TAG, "Making GET request with query params")
        AppLogger.infoLog(TAG, "URL:- $BASE_URL$url")

        val queryParams = APIManagerUtils.getMap(query)

        AppLogger.infoLog(TAG, "Query params:- $queryParams")
        apiInterface!!.get(url, queryParams).enqueue(callback)
    }

    fun <T> get(url: String, callback: ApiCallback<T>) {

        AppLogger.infoLog(TAG, "Making GET request without query params")
        AppLogger.infoLog(TAG, "URL:- $BASE_URL$url")

        apiInterface!!.get(url, HashMap()).enqueue(callback)
    }

    fun <T, V> post(url: String, payloadObj: T? = null, callback: ApiCallback<V>) {

        AppLogger.infoLog(TAG, "Making POST request")
        AppLogger.infoLog(TAG, "URL:- $BASE_URL$url")

        val payload =
            APIManagerUtils.getMap(payloadObj)

        AppLogger.infoLog(TAG, "Payload:- $payload")
        apiInterface!!.post(url, payload).enqueue(callback)
    }

    fun <T, V> put(url: String, payloadObj: T? = null, callback: ApiCallback<V>) {

        AppLogger.infoLog(TAG, "Making PUT request")
        AppLogger.infoLog(TAG, "URL:- $BASE_URL$url")

        val payload =
            APIManagerUtils.getMap(payloadObj)

        AppLogger.infoLog(TAG, "Payload:- $payload")
        apiInterface!!.put(url, payload).enqueue(callback)
    }

    fun <T, V> delete(url: String, payloadObj: T? = null, callback: ApiCallback<V>) {

        AppLogger.infoLog(TAG, "Making DELETE request")
        AppLogger.infoLog(TAG, "URL:- $BASE_URL$url")

        apiInterface!!.delete(url).enqueue(callback)
    }

    fun <V> multipart(url: String, payload: HashMap<String, RequestBody>,
                         file: MultipartBody.Part ,callback: ApiCallback<V>) {

        AppLogger.infoLog(TAG, "Making Multipart request")
        AppLogger.infoLog(TAG, "URL:- $BASE_URL$url")

        apiInterface!!.multipart(url, payload, file).enqueue(callback)
    }
}

