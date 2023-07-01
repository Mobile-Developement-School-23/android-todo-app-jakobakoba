package com.bor96dev.feature.repository_todo_items_impl.data.dto

import com.squareup.moshi.Json

internal data class TodoDto(
    @field:Json(name = "id")
    val id: String,
    @field: Json(name = "done")
    val done: Boolean,
    @field: Json(name = "text")
    val text: String,
    @field: Json(name = "created_at")
    val created_at: Long,
    @field: Json(name = "last_updated_by")
    val last_updated_by: String,
    @field: Json(name = "importance")
    val importance: String,
    @field: Json(name = "color")
    val color: String,
    @field: Json(name = "changed_at")
    val changed_at: Long
) {
    companion object {
        fun createMock(
            id: String,
            text: String,
            created_at: Long,
            changed_at: Long
        ): TodoDto {
            return TodoDto(
                id = id,
                text = text,
                done = false,
                created_at = created_at,
                changed_at = changed_at,
                importance = "basic",
                color = "#FFFFFF",
                last_updated_by = "Gabyshev_B"
            )
        }
    }
}