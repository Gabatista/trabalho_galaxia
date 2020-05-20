package com.cursoandroid.trabalho.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cursoandroid.trabalho.R
import kotlinx.android.synthetic.main.activity_pre_mapa.*

class activity_pre_extras : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_mapa)



        bt_mapas.setOnClickListener {
            startActivity(Intent(this,Mapa::class.java))
        }

        bt_chat.setOnClickListener {
            startActivity(Intent(this,ConversaActivity::class.java))
        }


    }




}
