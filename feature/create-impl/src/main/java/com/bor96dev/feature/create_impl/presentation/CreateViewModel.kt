package com.bor96dev.feature.create_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val router: Router,
    private val todoItemsInteractor: TodoItemsInteractor,
) : ViewModel() {

    fun onExitButtonClicked() {
        router.exit()
    }

    fun onSaveButtonClicked(
        text: String,
        priority: TodoItemPriority
    ) {
        viewModelScope.launch {
            todoItemsInteractor.addItem(text, priority)
            router.exit()
        }
    }
}