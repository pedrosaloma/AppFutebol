package com.example.simuladordepartidas.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jogo  (
    val descricao: String,
    val local: Local,
    val mandante: Time,
    val visitante: Time
        ) : Parcelable // extensao