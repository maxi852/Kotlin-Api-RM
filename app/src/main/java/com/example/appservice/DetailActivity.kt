package com.example.appservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name")
        val img = intent.getStringExtra("img")
        val specie = intent.getStringExtra("specie")
        val origin = intent.getStringExtra("origen")

        val nameTextView: TextView = findViewById(R.id.textName)
        val imgView: ImageView = findViewById(R.id.imageChar)
        val specieTextView: TextView = findViewById(R.id.etSpecie)
        val originTextView: TextView = findViewById(R.id.etOrigin)

        nameTextView.text = name
        specieTextView.text = specie
        originTextView.text = origin
        Glide.with(this).load(img).into(imgView)
    }
}