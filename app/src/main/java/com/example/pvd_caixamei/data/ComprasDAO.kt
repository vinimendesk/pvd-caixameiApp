package com.example.pvd_caixamei.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ComprasDAO {

    @Query("SELECT * FROM ComprasEntity")
    fun getAllCompras(): Flow<List<ComprasEntity>>

    @Insert fun addCompras(compras: ComprasEntity)

    @Delete fun deleteCompras(compras: ComprasEntity)

    @Update fun updateCompras(compras: ComprasEntity)

}