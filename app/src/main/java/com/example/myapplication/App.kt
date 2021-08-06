package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this)}
    val repository by lazy {BusinessCardRepository(database.businessCardDao())}
}