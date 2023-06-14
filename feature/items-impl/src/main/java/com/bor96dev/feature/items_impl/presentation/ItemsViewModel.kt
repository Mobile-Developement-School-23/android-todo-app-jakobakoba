package com.bor96dev.feature.items_impl.presentation

import androidx.lifecycle.ViewModel
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import javax.inject.Inject

internal class ItemsViewModel @Inject constructor(
    private val todoItemsInteractor: TodoItemsInteractor
) : ViewModel() {

}