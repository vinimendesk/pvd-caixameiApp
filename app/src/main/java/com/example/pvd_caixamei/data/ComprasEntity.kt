package com.example.pvd_caixamei.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class ComprasEntity (
    @PrimaryKey(autoGenerate = true)
    val compraId: Int = 0,
    val nomeCompra: String,
    val quantity: Int,
    val price: Double,
    val dateTime: LocalDateTime
)