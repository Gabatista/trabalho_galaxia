package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import kotlinx.android.synthetic.main.conteudo_chat.*
import kotlinx.android.synthetic.main.toolbar.*

class ConversaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        setSupportActionBar(toolbar)


        fabEnviar.setOnClickListener{
            EnviarMensagem()
        }


    }


    private fun EnviarMensagem(){
        val texto = String()
    }







}