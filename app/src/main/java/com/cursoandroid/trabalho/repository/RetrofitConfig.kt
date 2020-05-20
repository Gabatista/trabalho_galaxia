package com.cursoandroid.trabalho.repository

import android.content.Context
import com.cursoandroid.trabalho.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

open class RetrofitConfig(context: Context, baseUrl: String) {

    val retrofit: Retrofit
    val gson: Gson


    init {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()

        gson = GsonBuilder().create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    }
    
}