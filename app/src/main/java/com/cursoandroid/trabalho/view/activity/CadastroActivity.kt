package com.cursoandroid.trabalho.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.cadastro_activity.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var perfil: Perfil
    private lateinit var database : FirebaseDatabase
    private var uid = FirebaseAuth.getInstance().uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_activity)

        database = FirebaseDatabase.getInstance()

        bt_cadastro.setOnClickListener {
            cadastroUsuario()
        }
    }

    private fun cadastroUsuario() {
        val email = et_email.text.toString()
        val senha = et_senha.text.toString()
        val nome: String = et_nome.text.toString()

        if (!email.isEmpty() && !senha.isEmpty() && !nome.isEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
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
        var email = et_email.text.toString()
        var nome: String = et_nome.text.toString()

        val uid = FirebaseAuth.getInstance().uid
        var ref = FirebaseDatabase.getInstance().getReference("perfil")
        val id: String? = ref.push().key

        val perfil = Perfil(uid,email,nome)
        if (uid != null) {
            ref.child(uid).setValue(perfil).addOnCompleteListener {
                Toast.makeText(applicationContext,"Cadastro feito com sucesso",Toast.LENGTH_LONG).show()
            }
        }
    }

}
