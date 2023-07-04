package com.bor96dev.feature.database_impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bor96dev.feature.database_impl.dao.TodoItemDao
import com.bor96dev.feature.database_impl.entity.TodoItemEntity

@Database(entities = [TodoItemEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {

    companion object{
        private var INSTANCE: MyDatabase? = null

        fun get(context: Context): MyDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, MyDatabase::class.java, "database").build()
            }
            return INSTANCE as MyDatabase
        }
    }

    abstract fun todoItemDao(): TodoItemDao


}