package com.example.filmovi2021final.services

import com.example.filmovi2021final.film.FilmResponse
import retrofit2.Call
import retrofit2.http.GET

interface FilmApiInterface {

    @GET("/3/movie/popular?api_key=b92e25e9fc5d0071a6a624583baa1220")

    fun getMovieList() : Call<FilmResponse>
}