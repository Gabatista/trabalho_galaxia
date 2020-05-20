package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.view.adapter.ListaFotoAdapter
import com.cursoandroid.trabalho.viewmodel.FotoViewModel
import kotlinx.android.synthetic.main.lista_foto.*

class FotoPeriodo: AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(FotoViewModel::class.java)
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_foto)


        linearLayoutManager = LinearLayoutManager(this.applicationContext,
            LinearLayoutManager.VERTICAL,false)

        buscaFoto()
        configureRecyclerView()
    }

    private fun buscaFoto(){
        viewModel.result.observe(this, Observer { fotos ->
            val adapter = ListaFotoAdapter(fotos)
            adapter.notifyDataSetChanged()
            rv_foto.adapter = adapter
        })
        viewModel.getFotoPeriodo()
    }


    private fun configureRecyclerView(){
        rv_foto.layoutManager = LinearLayoutManager(this@FotoPeriodo)
    }
}