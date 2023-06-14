package com.bor96dev.feature.items_impl.presentation.model

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

internal data class ItemUi(
    val id: String,
    val text: String,
)

internal fun TodoItem.toUI(): ItemUi = ItemUi(id, text)