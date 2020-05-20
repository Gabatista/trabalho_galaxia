package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.ktx.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.trabalho.view.adapter.FotoAdapter
import com.cursoandroid.trabalho.viewmodel.FotoViewModel
import kotlinx.android.synthetic.main.foto_item.*
import kotlinx.android.synthetic.main.foto_item_lista.*
import kotlinx.android.synthetic.main.toolbar.*

class FotoActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.cursoandroid.trabalho.R.layout.foto_item)

        val actionBAR = supportActionBar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.cursoandroid.trabalho.R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

}