package com.cursoandroid.trabalho.repository

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.cursoandroid.trabalho.domain.Foto

import com.cursoandroid.trabalho.repository.dto.FotoDTO
import com.cursoandroid.trabalho.view.activity.FotoPeriodo
import com.cursoandroid.trabalho.view.activity.MainActivity
import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.Call
import retrofit2.Callback

import retrofit2.http.GET
import retrofit2.http.Query

import retrofit2.Response
import retrofit2.http.POST
import java.util.*


interface FotoApi {

    val data: String

    @GET("planetary/apod/")
    fun getFotoDoDia(
        @Query("api_key") apiKey: String = "gHnKKL3j0qZwm2OSeNHVrpxu11RHXsDduC9gldpK"
        ):  Call<FotoDTO>

    @GET("planetary/apod/")
    fun getFotoPeriodo(
        @Query("api_key") apiKey: String = "gHnKKL3j0qZwm2OSeNHVrpxu11RHXsDduC9gldpK",
        @Query("date") date: String = "2015-11-12"
        ): Call<FotoDTO>
}

abstract class Foto : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //intent.getStringExtra("data")

        val bundle: Bundle? = null

        val data = bundle!!.getString("dia")



    }


}


class FotoRepository(context: Context, baseUrl: String) : RetrofitConfig(context, baseUrl ){
    private val busca = retrofit.create(FotoApi::class.java)

    fun getFotoDia(callback: (fotos: Array<Foto>) -> Unit){
        busca.getFotoDoDia().enqueue(object : Callback<FotoDTO> {

            override fun onResponse(call: Call<FotoDTO>, response: Response<FotoDTO>) {

                val fotos = response?.body()
                val result = mutableListOf<Foto>()

                val dominio = Foto(
                    date = fotos?.date,
                    title = fotos?.title,
                    explanation = fotos?.explanation,
                    url = fotos?.url
                )
                result.add(dominio)
                callback(result.toTypedArray())
            }
            override fun onFailure(call: Call<FotoDTO>, t: Throwable) {
                callback(arrayOf())
            }
        })
    }


    fun getFotoPeriodo(callback: (fotos: Array<Foto>) -> Unit){
    busca.getFotoPeriodo().enqueue(object : Callback<FotoDTO> {

        override fun onResponse(call: Call<FotoDTO>, response: Response<FotoDTO>) {

            val fotos = response?.body()
            val result = mutableListOf<Foto>()

            val dominio = Foto(
                date = fotos?.date,
                title = fotos?.title,
                explanation = fotos?.explanation,
                url = fotos?.url

            )
            result.add(dominio)
            callback(result.toTypedArray())
        }

        override fun onFailure(call: Call<FotoDTO>, t: Throwable) {
            callback(arrayOf())
        }
    })
}
}


