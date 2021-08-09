package com.example.myapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao) {

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()

    fun delete(nome: String) = runBlocking {
        launch(Dispatchers.IO) {
            dao.deleteByName(nome)
        }
    }

}