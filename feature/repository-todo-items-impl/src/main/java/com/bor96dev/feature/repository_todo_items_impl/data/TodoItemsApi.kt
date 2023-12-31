package com.bor96dev.feature.repository_todo_items_impl.data

import com.bor96dev.feature.repository_todo_items_impl.data.dto.*
import com.bor96dev.feature.repository_todo_items_impl.data.response.*
import retrofit2.http.*

internal interface TodoItemsApi {

    @GET("list")
    suspend fun getList(): ListResponse

    @POST("list")
    suspend fun addElement(
        @Header("X-Last-Known-Revision") revision: Long,
        @Body request: AddElementRequest
    ): AddElementResponse

    @GET("list/{id}")
    suspend fun getElement(
        @Path("id") id: String
    ): GetElementResponse

    @DELETE("list/{id}")
    suspend fun deleteElement(
        @Header("X-Last-Known-Revision") revision: Long,
        @Path("id") id: String
    ): DeleteElementResponse

    @PUT("list/{id}")
    suspend fun updateElement(
        @Header("X-Last-Known-Revision") revision: Long,
        @Path("id") id: String,
        @Body request: AddElementRequest
    ): UpdateElementResponse

    @PATCH("list")
    suspend fun patch(
        @Header("X-Last-Known-Revision") revision: Long,
        @Body request: PatchRequest
    ): PatchResponse
}