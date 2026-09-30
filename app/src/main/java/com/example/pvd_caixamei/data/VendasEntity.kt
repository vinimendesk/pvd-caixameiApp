package com.example.pvd_caixamei.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class VendasEntity (
    @PrimaryKey(autoGenerate = true)
    val vendaId: Int = 0,
    val nomeVenda: String,
    /*val produtos: List<ProdutoEntity>,*/
    val valorVenda: Double,
    val dateTime: LocalDateTime
)