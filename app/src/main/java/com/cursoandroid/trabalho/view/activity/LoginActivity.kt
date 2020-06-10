package com.cursoandroid.trabalho.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        auth = FirebaseAuth.getInstance()

        verificaLogado()

        LOGAR.setOnClickListener {
            logar()
        }

        tv_sem_conta.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        tv_esqueceu_senha.setOnClickListener {
            val intent_1 = Intent(this, ResetaSenha::class.java)
            startActivity(intent_1)
        }

    }
        private fun logar(){
            val email = et_email.text.toString()
            val senha = et_senha.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this,"E-mail ou senha não preenchidos",Toast.LENGTH_LONG).show()
                return
            } else if(email.isEmpty()) {
                Toast.makeText(this,"Preencha o campo e-mail",Toast.LENGTH_LONG).show()
                return
            } else if(senha.isEmpty()) {
                Toast.makeText(this,"Preencha o campo senha",Toast.LENGTH_LONG).show()
                return
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this,"Login efetuado com sucesso",Toast.LENGTH_LONG).show()
                        abreTelaInicial()
                    }else{
                    }

                }.addOnFailureListener {

                    Toast.makeText(this,"Erro ao entrar, tente mais tarde",Toast.LENGTH_LONG).show()
                }

        }

    private fun abreTelaInicial(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    public override fun onStart() {
        super.onStart()
        val usuarioAtual = auth.currentUser
        verificaLogado()
    }

    private fun verificaLogado(){
        val autenticado = FirebaseAuth.getInstance().currentUser
        if(autenticado == null){
        }else{
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}