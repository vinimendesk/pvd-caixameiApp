package com.example.pvd_caixamei.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["compraId", "produtoId"],
    foreignKeys = [
        ForeignKey(
            entity = ComprasEntity::class,
            parentColumns = ["compraId"],
            childColumns = ["compraId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProdutoEntity::class,
            parentColumns = ["produtoId"],
            childColumns = ["produtoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CompraProdutoEntity (
    val compraId: Int,
    val produtoId: Int,
    val quantidade: Int
    )