package com.cursoandroid.trabalho.view.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.repository.FotoRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        var cal = Calendar.getInstance()
        cal.get(Calendar.YEAR)
        cal.get(Calendar.MONTH)
        cal.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, (monthOfYear))
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val formato = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                val data = formato.format(cal.time).toString()

                Toast.makeText(applicationContext,data, Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, FotoRepository::class.java)
                intent.putExtra("dia",data)


                iniciaActivity()
            }

        }

        bt_foto_periodo.setOnClickListener {
            DatePickerDialog(this@MainActivity, dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        bt_foto_dia.setOnClickListener {
            startActivity(Intent(this,FotoDia::class.java))
        }

        bt_interesses.setOnClickListener {
            startActivity(Intent(this,activity_pre_extras::class.java))
        }

    }

    private fun iniciaActivity(){
        startActivity(Intent(this,FotoPeriodo::class.java))
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sair ->{
                deslogar()
                return true
            }
            R.id.perfil ->{
                abrirPerfil()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deslogar(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        Toast.makeText(this,"Deslogado com sucesso",Toast.LENGTH_LONG).show()
    }

    private fun abrirPerfil(){
        startActivity(Intent(this, PerfilActivity::class.java))
    }
}
