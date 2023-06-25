package com.bor96dev.feature.repository_todo_items_api

import com.bor96dev.core.di.FeatureDependency

interface RepositoryTodoItemsApiProvider : FeatureDependency {

    fun repositoryTodoItemsApi(): RepositoryTodoItemsApi

}