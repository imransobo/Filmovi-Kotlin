package com.example.filmovi2021final.film

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmovi2021final.R

import com.example.filmovi2021final.databinding.FilmItemBinding


class FilmAdapter(private val filmovi: List<FilmPodaci>, var clickedListener: OnItemClickedListener): RecyclerView.Adapter<FilmAdapter.MyViewHolder>() {

    private var films = ArrayList<FilmPodaci>()



    fun setListData(film: ArrayList<FilmPodaci>) {
        this.films = film
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(inflater)


        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trenutni_film = filmovi[position]



        if(trenutni_film != null) {
            holder.bind(filmovi.get(position), clickedListener)
        }


    }

    override fun getItemCount(): Int = filmovi.size


    inner class MyViewHolder(val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val IMAGE_BASE = "http://image.tmdb.org/t/p/w500"


        fun bind(film: FilmPodaci, action: OnItemClickedListener) {
            binding.apply {

                imeFilmaPodaci.text = film.title
                godinaIzdanjaPodaci.text = film.release

                Glide.with(moviePoster)
                        .load(IMAGE_BASE + film.posterPath)
                        .into(moviePoster)


            }

            itemView.setOnClickListener {
                action.onItemClick(film, bindingAdapterPosition)
            }

        }


    }



    class FilmComparator: DiffUtil.ItemCallback<FilmPodaci>() {
        override fun areItemsTheSame(oldItem: FilmPodaci, newItem: FilmPodaci) =
                oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: FilmPodaci, newItem: FilmPodaci) =
                oldItem == newItem

    }






}

interface OnItemClickedListener {
    fun onItemClick(film:FilmPodaci, position: Int)
}