package com.example.cartaodevisitas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BusinessCard::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun businessCardDao(): BusinessCardDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Se a instancia não é nula, a retorna,
            // caso contrário, cria uma e retorna
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "businesscard_db"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }

    }
}