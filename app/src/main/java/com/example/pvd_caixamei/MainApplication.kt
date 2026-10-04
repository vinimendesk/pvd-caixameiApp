package com.example.pvd_caixamei

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.pvd_caixamei.data.PvdDatabase

class MainApplication: Application() {

    companion object {
        lateinit var pvdDatabase: PvdDatabase
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("MainApplication", "MainApplication.onCreate() foi executado")

        pvdDatabase = Room.databaseBuilder(
            applicationContext,
            PvdDatabase::class.java,
            "pvd-database"
        )

            .fallbackToDestructiveMigration()
            .build()

        Log.d("MainApplication", "pvdDatabase foi inicializado")
    }

}