package com.example.filmovi2021final.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmAPIService {

    companion object {

        private const val BASE_URL = "http://api.themoviedb.org"
        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {
            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }


            //sigurni smo da nece biti null pa se radi null check i baca NullPointerException
            //ako se nadje null vrijednost
            return retrofit!!
        }


    }
}