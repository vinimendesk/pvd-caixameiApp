package com.example.pvd_caixamei.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class VendasEntity (
    @PrimaryKey(autoGenerate = true)
    val vendaId: Int = 0,
    val nomeVenda: String,
    val quantity: Int,
    val valorVenda: Double,
    val dateTime: LocalDateTime
)