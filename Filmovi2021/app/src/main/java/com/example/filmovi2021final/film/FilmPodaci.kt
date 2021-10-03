package com.example.filmovi2021final.film


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "lista_filmova")
data class FilmPodaci(
    @ColumnInfo(name = "id_filma")
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "ime_filma")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "id_slike")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "godina_izdanja")
    @SerializedName("release_date")
    val release: String?,

    @ColumnInfo(name="radnja_filma")
    @SerializedName("overview")
    val overview: String?,






) : Parcelable {
    constructor() : this(0,"",""," ","")


}