package com.example.hola.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hola.model.User

@Dao
interface BodegaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBodega(user: User)

    @Update
    suspend fun updateBodega(user: User)

    @Delete
    suspend fun deleteBodega(user: User)

    @Query("DELETE FROM bodega_table")
    suspend fun deleteAllBodegas()

    @Query("SELECT * FROM bodega_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

}