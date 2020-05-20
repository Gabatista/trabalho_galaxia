package com.cursoandroid.trabalho.interactor

import android.content.Context
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Foto
import com.cursoandroid.trabalho.repository.FotoRepository
import java.util.ArrayList

class FotoInteractor(private val context: Context) {

    private val repositorioFoto = FotoRepository(context,context.getString(R.string.url_base))


   fun getFoto(calback: (fotos: Array<Foto>) -> Unit){
       repositorioFoto.getFotoDia(calback)

   }

    fun getFotoPeriodo(calback: (fotos: Array<Foto>) -> Unit){
        repositorioFoto.getFotoPeriodo(calback)

    }

}