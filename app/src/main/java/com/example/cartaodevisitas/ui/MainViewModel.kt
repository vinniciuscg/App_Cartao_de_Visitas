package com.example.cartaodevisitas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cartaodevisitas.data.BusinessCard
import com.example.cartaodevisitas.data.BusinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(
    private val businessCardRepository: BusinessCardRepository
) : ViewModel() {

    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll()
    }

    fun deleteByName(nome: String){
        businessCardRepository.delete(nome)
    }
}

class MainViewModelFactory(
    private val repository: BusinessCardRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel lass")
    }

}