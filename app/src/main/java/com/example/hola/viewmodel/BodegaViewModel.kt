package com.example.hola.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hola.data.BodegaDatabase
import com.example.hola.model.User
import com.example.hola.repository.BodegaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BodegaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: BodegaRepository

    init {
        val bodegaDao = BodegaDatabase.getDatabase(
            application
        ).bodegaDao()
        repository = BodegaRepository(bodegaDao)
        readAllData = repository.readAllData
    }

    fun addBodega(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBodega(user)
        }
    }

    fun updateBodega(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBodega(user)
        }
    }

    fun deleteBodega(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBodega(user)
        }
    }

    fun deleteAllBodegas(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllBodegas()
        }
    }

}