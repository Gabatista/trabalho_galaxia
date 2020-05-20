package com.cursoandroid.trabalho.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cadastro_activity.*

class CadastroActivity : AppCompatActivity() {

    private var perfil: Perfil ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_activity)

        bt_cadastro.setOnClickListener {
            cadastroUsuario()
        }
    }

    private fun cadastroUsuario() {
        val email = et_email.text.toString()
        val senha = et_senha.text.toString()

        if (!email.isEmpty() && !senha.isEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Cadastro feito com sucesso", Toast.LENGTH_LONG).show()
                        salvarDados()
                        abreTelaInicial()
                        finish()
                    }
                }
        } else {
            Toast.makeText(this, "Por favor, preencha os campos", Toast.LENGTH_LONG).show()
            return

        }
    }

    private fun abreTelaInicial(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun salvarDados(){

        perfil = Perfil(
            email = et_email.text.toString(),
            nome = et_nome.text.toString()
            //visualizacoes = null
        )

        val ref = FirebaseDatabase.getInstance().getReference().child("perfil")
        ref.push().setValue(perfil)

    }

}
