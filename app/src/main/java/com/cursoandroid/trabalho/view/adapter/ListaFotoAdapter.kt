package com.cursoandroid.trabalho.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Foto
import com.cursoandroid.trabalho.view.activity.MainActivity
import com.squareup.picasso.Picasso

class ListaFotoAdapter(val fotos: Array<Foto>) :
    RecyclerView.Adapter<ListaFotoAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.foto_linha, parent, false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return fotos.count()


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foto = fotos.get(position)
        holder.title.text = foto.title
        holder.descricao.text = foto.explanation
        holder.data.text = foto.date

        Picasso.get().load(foto.url).into(holder.foto)


    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.titulo_123)
        val descricao = itemView.findViewById<TextView>(R.id.descricao_123)
        val foto = itemView.findViewById<ImageView>(R.id.foto_123)
        val botao_visto = itemView.findViewById<Button>(R.id.bt_visto)
        val data =  itemView.findViewById<TextView>(R.id.tv_data)

        private fun salvarSair(){
            //salvarFirebase
            retornaPrincipal()
        }

        private fun retornaPrincipal(){
            val intent = Intent(itemView.context, MainActivity::class.java)
            itemView.context.startActivity(intent)
        }

        private fun salvarFirebase(){
Log.d("teste","teste")
        }



        init {
            botao_visto.setOnClickListener {
salvarFirebase()

            }
        }

    }



}