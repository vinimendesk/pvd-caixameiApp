package com.example.pvd_caixamei.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    ProdutoEntity::class,
    VendasEntity::class,
    ComprasEntity::class],
    version = 1)
abstract class PvdDatabase: RoomDatabase() {
    abstract fun getProdutoDAO(): ProdutoDAO
    abstract fun getComprasDAO(): ComprasDAO
    abstract fun getVendasDAO(): VendasDAO
}