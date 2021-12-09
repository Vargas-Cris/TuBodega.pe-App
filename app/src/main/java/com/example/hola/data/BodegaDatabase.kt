package com.example.hola.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hola.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class BodegaDatabase : RoomDatabase() {

    abstract fun bodegaDao(): BodegaDao

    companion object {
        @Volatile
        private var INSTANCE: BodegaDatabase? = null

        fun getDatabase(context: Context): BodegaDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BodegaDatabase::class.java,
                    "bodega_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}