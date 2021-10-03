package com.example.filmovi2021final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.filmovi2021final.databinding.ActivityDetaljiBinding
import com.example.filmovi2021final.film.FilmPodaci
import com.example.filmovi2021final.film.FilmoviViewModel
import kotlinx.coroutines.InternalCoroutinesApi

class DetaljiActivity() : AppCompatActivity() {

    private lateinit var binding : ActivityDetaljiBinding

    private lateinit var viewModel: FilmoviViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle("Detalji")
        binding = ActivityDetaljiBinding.inflate(layoutInflater)

        binding.detaljiNaslovFilma.text = intent.getStringExtra("Naslov")
        binding.detaljiGodinaIzdanja.text = intent.getStringExtra("Godina")
        binding.detaljiRadnjaFilma.text = intent.getStringExtra("Radnja")


        viewModel = ViewModelProvider(this).get(FilmoviViewModel::class.java)

        //zbog slike se crashuje
        //binding.detaljiSlika.setImageResource(intent.getStringExtra("Slika").toInt()!!)

        binding.detaljiSpasiFilm.setOnClickListener {
            val imeFilma = binding.detaljiNaslovFilma.toString()
            val godinaIzdanja = binding.detaljiGodinaIzdanja.toString()
            val radnja = binding.detaljiRadnjaFilma.toString()
            val slika = binding.detaljiSlika.toString()

            Log.i("KLIK", "Prije save-a")
            val film = FilmPodaci(2, imeFilma, slika, godinaIzdanja, radnja)
            viewModel.insertMovieData(film)
            Log.i("KLIK", "Spaseno u bazu")
        }

        setContentView(binding.root)
    }
}