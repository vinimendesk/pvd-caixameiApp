package com.example.pvd_caixamei

import android.app.Application
import androidx.room.Room
import com.example.pvd_caixamei.data.PvdDatabase

class MainApplication: Application() {

    companion object {
        lateinit var pvdDatabase: PvdDatabase
    }

    override fun onCreate() {
        super.onCreate()
        pvdDatabase = Room.databaseBuilder(
            applicationContext,
            PvdDatabase::class.java,
            "pvd-database"
        )

            .fallbackToDestructiveMigration()
            .build()
    }

}