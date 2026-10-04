package com.example.pvd_caixamei.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM ProdutoEntity")
    fun getAllProduto(): Flow<List<ProdutoEntity>>

    @Query("SELECT * FROM ProdutoEntity WHERE categoria = :categoriaProduto")
    fun getAllProdutoByCategoria(categoriaProduto: String) : Flow<List<ProdutoEntity>>

    @Insert fun addProduto(produto: ProdutoEntity)

    @Delete fun deleteProduto(produto: ProdutoEntity)

    @Query("DELETE FROM ProdutoEntity")
    fun deleteAllProducts()

    @Update fun updateProduto(produto: ProdutoEntity)

}