package com.example.appservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var charactersList = mutableListOf<Results>()

    val job = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getCharacters()
        adapter = Adapter(this@MainActivity,charactersList)
        recyclerView.adapter = adapter

    }

    private fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getListOfCharacters("character/?status=alive")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val charactersMap = response?.results
                    charactersList.clear()
                    if (charactersMap != null) {
                        charactersMap.map {
                            charactersList.add(it)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_RICK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val URL_RICK = "https://rickandmortyapi.com/api/"
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}


