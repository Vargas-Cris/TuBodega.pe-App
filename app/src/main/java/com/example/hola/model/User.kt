package com.example.hola.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "bodega_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val ruc: String,
    val nombre: String,
    val direccion :String,
    val region:String,
    val provincia:String,
    val distrito:String,
    val producto: String
): Parcelable