package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM BusinessCard WHERE id IN (:cardIds)")
    fun LoadAllByIds(cardIds: IntArray): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM BusinessCard WHERE nome LIKE (:nomeBusca)")
    fun findByName(nomeBusca: String): LiveData<List<BusinessCard>>

    //implementar update

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businessCards: BusinessCard)

    @Delete
    fun delete(businessCard: BusinessCard)
}