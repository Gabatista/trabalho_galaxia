package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.reseta_senha.*

class ResetaSenha :AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reseta_senha)

        auth = FirebaseAuth.getInstance()

        bt_envia_email.setOnClickListener {
            enviaEmail()
        }
    }

    private fun enviaEmail(){
        val email = et_mail_reseta.text.toString()

        if(email.isEmpty()){
            Toast.makeText(this,"Digite o email",Toast.LENGTH_LONG).show()
            return
        } else{
            auth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Email enviado com sucesso",Toast.LENGTH_LONG).show()
                        finish()
                    } else{
                        Toast.makeText(this,"Email nao enviado ",Toast.LENGTH_LONG).show()

                    }
                }
        }
    }

}