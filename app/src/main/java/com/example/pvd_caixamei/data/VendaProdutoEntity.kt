package com.example.pvd_caixamei.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["vendaId", "produtoId"],
    foreignKeys = [
        ForeignKey(
            entity = VendasEntity::class,
            parentColumns = ["vendaId"],
            childColumns = ["vendaId"],
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
data class VendaProdutoEntity(
    val vendaId: Int,
    val produtoId: Int,
    val quantidade: Int
)