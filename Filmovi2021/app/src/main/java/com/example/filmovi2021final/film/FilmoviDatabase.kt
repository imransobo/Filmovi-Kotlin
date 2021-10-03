package com.example.filmovi2021final.film

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [FilmPodaci::class], version = 2, exportSchema = false)
abstract class FilmoviDatabase : RoomDatabase() {

    abstract val filmoviDatabaseDao: FilmoviDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: FilmoviDatabase?= null                                                //null je na pocetku, poslije pravimo instancu


                                                                                                    //sa getInstance nikad ne mozemo dobiti null, uvijek ce se napraviti baza
        @InternalCoroutinesApi
        fun getInstance(context: Context): FilmoviDatabase {
                                                                                                    //zakljucano, dok se ovo ne izvrsi , ne moze niko drugi pokrenuti
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FilmoviDatabase::class.java,
                        "lista_filmova"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()                                           //izbrisi sve  i ponovo pocni unositi polja
                        .build()                                                                    //vraca bazu podataka

                    INSTANCE = instance                                                             //ovim osiguravamo da ne bude null (androidova praksa)
                }
                return instance
            }


        }

    }



}