package com.bor96dev.feature.repository_todo_items_impl

data class TodoItemData(
    val id: String,
    val name: String,
    val isDone: Boolean,
    val changedAt: Long
)