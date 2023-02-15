package com.spoofsense.liveness.network

import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.liveness.constants.URLConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object APIInstance {

    val api: API by lazy {
        Retrofit.Builder()
            .baseUrl(URLConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient().build())
            .build()
            .create(API::class.java)
    }



    private fun getClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("x-api-key", SpoofSense.apiKey)
                    .build()
                chain.proceed(newRequest)
            }
        httpClient.readTimeout(60,TimeUnit.SECONDS)
        httpClient.connectTimeout(60,TimeUnit.SECONDS)
        return httpClient
    }



}