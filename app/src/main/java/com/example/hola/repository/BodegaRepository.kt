package com.example.hola.repository

import androidx.lifecycle.LiveData
import com.example.hola.data.BodegaDao
import com.example.hola.model.User

class BodegaRepository(private val bodegaDao: BodegaDao) {

    val readAllData: LiveData<List<User>> = bodegaDao.readAllData()

    suspend fun addBodega(user: User){
        bodegaDao.addBodega(user)
    }

    suspend fun updateBodega(user: User){
        bodegaDao.updateBodega(user)
    }

    suspend fun deleteBodega(user: User){
        bodegaDao.deleteBodega(user)
    }

    suspend fun deleteAllBodegas(){
        bodegaDao.deleteAllBodegas()
    }

}