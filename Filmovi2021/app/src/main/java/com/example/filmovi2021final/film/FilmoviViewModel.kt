package com.example.filmovi2021final.film

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.filmovi2021final.services.FilmAPIService
import com.example.filmovi2021final.services.FilmApiInterface
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

public class FilmoviViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var sviFilmovi: MutableLiveData<List<FilmPodaci>>

    init {
        sviFilmovi = MutableLiveData()
    }

    fun getAllMoviesObservers() : MutableLiveData<List<FilmPodaci>> {
        return sviFilmovi
    }

    @InternalCoroutinesApi
    fun getAllMovies() {
        val filmDao = FilmoviDatabase.getInstance((getApplication()))?.filmoviDatabaseDao
        val list = filmDao.getAll()

        sviFilmovi.postValue(list!!)
    }


    @InternalCoroutinesApi
    fun insertMovieData(film: FilmPodaci) {
        val filmDao = FilmoviDatabase.getInstance((getApplication()))?.filmoviDatabaseDao
        filmDao.insertFilm(film)
        getAllMovies()
    }

    @InternalCoroutinesApi
    fun updateMovieData(film: FilmPodaci) {
        val filmDao = FilmoviDatabase.getInstance((getApplication()))?.filmoviDatabaseDao
        filmDao.updateFilm(film)
        getAllMovies()
    }




    fun getMovieData(callback: (List<FilmPodaci>) -> Unit) {

        val apiService = FilmAPIService.getInstance().create(FilmApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<FilmResponse> {

            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                Log.e("getMovieData", "USPJELO")
                return callback(response.body()!!.filmovi)

            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                Log.e("getMovieData", "NEUSPJESNO")

            }

        })







    }

}