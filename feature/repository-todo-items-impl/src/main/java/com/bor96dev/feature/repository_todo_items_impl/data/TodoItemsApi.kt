package com.bor96dev.feature.repository_todo_items_impl.data

import retrofit2.http.GET

internal interface TodoItemsApi {

    @GET("list")
    suspend fun getList(): ListResponse
}