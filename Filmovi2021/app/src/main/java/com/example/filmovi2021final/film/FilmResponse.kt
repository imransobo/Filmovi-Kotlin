package com.example.filmovi2021final.film

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "lista_filmova")
data class  FilmResponse(
    @SerializedName("results")
    val filmovi : List<FilmPodaci>

) : Parcelable {
    constructor() : this(mutableListOf())
}