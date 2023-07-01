package com.bor96dev.feature.repository_todo_items_impl.di

import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.feature.repository_todo_items_impl.data.request.OkHttpClientBuilder
import com.bor96dev.feature.repository_todo_items_impl.data.TodoItemsApi
import com.bor96dev.feature.repository_todo_items_impl.data.TodoItemsRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
internal class TodoItemsModule {

    @PerFeature
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClientBuilder.get()

    @PerFeature
    @Provides
    fun provideTodoItemsApi(
        okHttpClient: OkHttpClient
    ): TodoItemsApi {
        return Retrofit.Builder()
            .baseUrl("https://beta.mrdekk.ru/todobackend/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @PerFeature
    @Provides
    fun provideRepository(impl: TodoItemsRepositoryImpl): TodoItemsRepository = impl
}