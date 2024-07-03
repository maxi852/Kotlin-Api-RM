package com.example.appservice

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class Adapter(val context: Context, val listCharacters: List<Results>): RecyclerView.Adapter<Adapter.AdapViewHolder>() {

    inner class AdapViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val imageView: ImageView = view.findViewById(R.id.item_image)
         val nameView: TextView = view.findViewById(R.id.item_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedCharacter = listCharacters[position]
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("name", selectedCharacter.name)
                        putExtra("img", selectedCharacter.image)
                        putExtra("specie", selectedCharacter.species)
                        putExtra("origen", selectedCharacter.origin.name)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AdapViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: AdapViewHolder, position: Int) {

        val item = listCharacters[position]
        Log.i("MainActivity",item.toString())
        holder.nameView.text = item.name
        Glide.with(context).load(item.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }
}