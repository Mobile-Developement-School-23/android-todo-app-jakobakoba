package com.bor96dev.yandextodoapp.core.feature.todo_items_api

import com.bor96dev.core.di.FeatureDependency

interface TodoItemsApiProvider : FeatureDependency {

    fun todoItemsApi(): TodoItemsApi
}