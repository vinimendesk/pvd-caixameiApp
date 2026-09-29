package com.example.pvd_caixamei.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProdutoEntity(
    @PrimaryKey(autoGenerate = true)
    val produtoId: Int = 0,
    val nome: String,
    val categoria: String,
    val price: Double,
    val estoque: Int
)
