package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.perfil.*

class PerfilActivity : AppCompatActivity(){

    private var perfil : Perfil ? = null
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()


    private val ref = FirebaseDatabase.getInstance()
        .getReference("perfil").child(FirebaseAuth.getInstance().currentUser!!.uid)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)


        //recuperaDados()
        //recuperarDados()
        recuper1()
    }



    private fun recuper1(){
        val email = auth.currentUser?.email
        tv_email.setText(email)

         val raiz = FirebaseDatabase.getInstance().reference
         val ordem = raiz.child("perfil").child(FirebaseAuth.getInstance().currentUser!!.uid)
         val valueEventListener = object : ValueEventListener{
             override fun onCancelled(p0: DatabaseError) {
                 TODO("Not yet implemented")
             }

             override fun onDataChange(p0: DataSnapshot) {
                val perfil = p0.getValue(Perfil::class.java)
                 tv_usuario.text = perfil?.nome
             }

         }
        ordem.addListenerForSingleValueEvent(valueEventListener)


    }


    private fun recuperarDados(){
        val email = auth.currentUser?.email
        tv_email.setText(email)


        val perfis = database.getReference("perfil").child(auth.currentUser!!.uid)

        val ref = FirebaseDatabase.getInstance().getReference("perfil").child(auth.currentUser!!.uid).child("nome")

        val listener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                perfil = p0.children.first().getValue(Perfil::class.java)

            }

        }
        ref.addListenerForSingleValueEvent(listener)

  //      val perfis = database.getReference("perfil")
  //      val query = perfis.orderByChild("email").equalTo(email)
/*
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@PerfilActivity, "Consulta cancelada!", Toast.LENGTH_LONG).show()

            }

            override fun onDataChange(p0: DataSnapshot) {
                perfil = p0.children.first().getValue(Perfil::class.java)
                if(perfil != null){
                    tv_usuario.setText(perfil?.nome)
                }
            }

        })
*/
    }


/*
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



*/


    }



