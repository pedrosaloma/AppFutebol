package com.example.simuladordepartidas.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Time(
    val nome: String,
    val estrelas: Int,
    val imagem: String,
    var placar: Int? // aceita nulo
) : Parcelable
