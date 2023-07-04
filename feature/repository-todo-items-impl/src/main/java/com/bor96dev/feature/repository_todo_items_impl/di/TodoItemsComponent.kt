package com.bor96dev.feature.repository_todo_items_impl.di

import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.database_api.DatabaseApi
import com.bor96dev.feature.repository_todo_items_api.RepositoryTodoItemsApi
import dagger.Component

@PerFeature
@Component(
    dependencies = [
        DatabaseApi::class
    ],
    modules = [
        TodoItemsModule::class
    ]
)
internal interface TodoItemsComponent : RepositoryTodoItemsApi {

    @Component.Factory
    interface Builder {
        fun create(databaseApi: DatabaseApi): TodoItemsComponent
    }
}