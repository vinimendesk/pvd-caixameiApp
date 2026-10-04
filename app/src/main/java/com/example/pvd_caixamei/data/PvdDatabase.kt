package com.example.pvd_caixamei.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [
    ProdutoEntity::class,
    VendasEntity::class,
    ComprasEntity::class,
    VendaProdutoEntity::class,
    CompraProdutoEntity::class],
    version = 2)
@TypeConverters(Converters::class)
abstract class PvdDatabase: RoomDatabase() {
    abstract fun getProdutoDAO(): ProdutoDAO
    abstract fun getComprasDAO(): ComprasDAO
    abstract fun getVendasDAO(): VendasDAO
}