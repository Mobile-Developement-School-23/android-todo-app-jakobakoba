package com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain

private const val URGENT = "urgent"
private const val BASIC = "basic"
private const val LOW = "low"

enum class TodoItemPriority {
    LOW,
    NORMAL,
    URGENT
}

fun String.priorityToDomain(): TodoItemPriority {
    return when (this) {
        LOW -> TodoItemPriority.LOW
        BASIC -> TodoItemPriority.NORMAL
        else -> TodoItemPriority.URGENT
    }
}

fun TodoItemPriority.toData(): String {
    return when (this) {
        TodoItemPriority.URGENT -> URGENT
        TodoItemPriority.NORMAL -> BASIC
        else -> LOW
    }
}