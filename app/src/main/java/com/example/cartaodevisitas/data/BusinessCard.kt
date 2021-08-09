package com.example.cartaodevisitas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String,
    val empresa: String,
    val corDeFundo: String
)
