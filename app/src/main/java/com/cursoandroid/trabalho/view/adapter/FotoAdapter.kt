package com.cursoandroid.trabalho.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cursoandroid.trabalho.domain.Foto
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.trabalho.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.foto_item_lista.view.*
import kotlinx.android.synthetic.main.foto_linha.view.*


class FotoAdapter(private val fotos: Array<Foto>)
        : RecyclerView.Adapter<FotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.foto_item_lista, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fotos.size
    }

    override fun onBindViewHolder(holder: FotoAdapter.ViewHolder, position: Int) {
        val foto = fotos[position]
        holder.titulo.text = foto.title
        holder.descricao.text = foto.explanation
        holder.data.text = foto.date

        Picasso.get().load(foto.url).resize(50, 50).into(holder.foto)

    }


    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foto: ImageView = itemView.fotoooo
        val titulo: TextView = itemView.descricaooo
        val descricao: TextView = itemView.descricaooo
        val data: TextView = itemView.tv_data


    }
    

}






