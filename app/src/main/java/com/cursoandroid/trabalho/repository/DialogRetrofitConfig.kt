package com.cursoandroid.trabalho.repository

import android.content.Context
import com.cursoandroid.trabalho.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class DialogRetrofit(context: Context){
    val retorfit: Retrofit
    val gson: Gson

    init {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()

        retorfit = Retrofit.Builder()
            .client(client)
            .baseUrl(context.getString(R.string.title_activity_mapa))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun ToJSON(obj: Any): String = gson.toJson(obj)
    inline fun <reified T> fromJson(json:String) = gson.fromJson(json,T::class.java)


}