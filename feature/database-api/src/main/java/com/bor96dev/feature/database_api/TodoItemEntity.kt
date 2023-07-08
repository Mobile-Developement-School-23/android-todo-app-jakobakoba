package com.bor96dev.feature.database_api


data class TodoItemEntity(
    val id: String,
    val name: String,
    val isDone: Boolean,
    val priority: String,
    val changedAt: Long
)
