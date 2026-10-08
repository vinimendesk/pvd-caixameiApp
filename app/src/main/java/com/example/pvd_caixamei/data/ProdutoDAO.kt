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

    @Update
    fun updateProduto(produto: ProdutoEntity)

    // Diminui o estoque do produto.
    // A condição "estoque >= :quantidade" garante que
    // nunca seja possível deixar o estoque negativo.
    // O retorno é a quantidade de linhas alteradas:
    // 1 = estoque atualizado com sucesso
    // 0 = produto não existe ou estoque insuficiente
    @Query("""
        UPDATE ProdutoEntity
        SET estoque = estoque - :quantidade
        WHERE produtoId = :produtoId
        AND estoque >= :quantidade
    """)
    fun diminuirEstoque(
        produtoId: Int,
        quantidade: Int
    ): Int

    @Query("""
    UPDATE ProdutoEntity
    SET estoque = estoque + :quantidade
    WHERE produtoId = :produtoId
""")
    fun aumentarEstoque(
        produtoId: Int,
        quantidade: Int
    )

}