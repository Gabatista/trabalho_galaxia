package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.perfil.*

class PerfilActivity : AppCompatActivity(){

    private lateinit var perfil: Perfil
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val ref = FirebaseDatabase.getInstance()
        .getReference("perfil").child(FirebaseAuth.getInstance().currentUser!!.uid)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        fun usuarioAtual(): DatabaseReference = database.child("perfil")
            .child(auth.currentUser!!.uid)

        recuperaDados()

    }

    fun usuarioAtual(): DatabaseReference = database.child("perfil")
        .child(auth.currentUser!!.uid)

    private fun recuperaDados(){

        usuarioAtual().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {

                perfil = p0.child("perfil").getValue(Perfil::class.java)!!
                if (perfil != null) {
                    tv_email.setText(perfil!!.email)
                    tv_usuario.setText(perfil!!.nome)
                }

            }
        })


    }






    }



