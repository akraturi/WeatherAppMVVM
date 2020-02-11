package com.parviom.pwnetwork.core


import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.AppLogger


class RetrofitClient// cannot be instantiated
private constructor() {

    companion object {

        private val TAG = RetrofitClient::class.java.getSimpleName()

        fun getInstance(baseUrl: String, headers: Map<String, String>): Retrofit? {

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    getClient(
                        headers
                    )
                )
                .build()
        }

        private fun getClient(headers: Map<String, String>): OkHttpClient {

            val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

            httpClientBuilder.addInterceptor { chain ->
                val orignal: Request = chain.request()
                val requestBuilder: Request.Builder = orignal.newBuilder()
                for (item: Map.Entry<String, String> in headers.entries) {
                    requestBuilder.addHeader(item.key, item.value)
                }
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }

            val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {


//                if (!it.startsWith("{")) {
//                    AppLogger.infoLog(TAG,it)
//                    return@Logger
//                }
//                try {
//                    val prettyPrintJson = GsonBuilder().setPrettyPrinting().create().toJson(it)
//                    AppLogger.infoLog(TAG,prettyPrintJson)
//                } catch (m: JsonSyntaxException) {
//                    AppLogger.infoLog(TAG,it)
//                }
                AppLogger.infoLog(TAG,it)
            })

            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logging)

            return httpClientBuilder.build()
        }
    }

}