package com.bor96dev.feature.database_impl.di

import android.content.Context
import androidx.room.Room
import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.database_impl.DatabaseRepositoryImpl
import com.bor96dev.feature.database_impl.dao.TodoItemDao
import com.bor96dev.feature.database_impl.data.TodoItemDatabase
import dagger.Module
import dagger.Provides

@Module
internal class DatabaseModule {

    @PerFeature
    @Provides
    fun provideDatabaseRepository(impl: DatabaseRepositoryImpl): DatabaseRepository = impl

//    @PerFeature
//    @Provides
//    fun provideTodoItemDao(context: Context): TodoItemDao {
//        return Room.databaseBuilder(
//            context,
//            TodoItemDatabase::class.java, "database"
//        ).build()
//            .todoItemDao()
//    }
}