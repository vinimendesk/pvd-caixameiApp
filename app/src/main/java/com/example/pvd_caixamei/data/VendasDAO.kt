package com.example.pvd_caixamei.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VendasDAO {

    @Query("SELECT * FROM VendasEntity")
    fun getAllVendas(): Flow<List<VendasEntity>>

    @Insert fun addVendas(venda: VendasEntity)

    @Delete fun deleteProduto(produto: ProdutoEntity)

    @Update fun updateProduto(produto: ProdutoEntity)

}