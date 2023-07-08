package com.bor96dev.feature.create_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateViewModel @AssistedInject constructor(
    @Assisted("id") private val id: String,
    private val router: Router,
    private val todoItemsInteractor: TodoItemsInteractor,
) : ViewModel() {

    private val _screenState = MutableStateFlow(TodoItem.EMPTY)
    val state: MutableStateFlow<TodoItem> = _screenState

    fun recreateScreen(id: String) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                val item = withContext(Dispatchers.IO) { todoItemsInteractor.getItem(id) }
                _screenState.emit(item)
            }
        }
    }

    fun removeItemButtonClicked(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemsInteractor.removeItem(id)
        }
        router.exit()
    }

    fun onExitButtonClicked() {
        router.exit()
    }

    fun onSaveButtonClicked(
        text: String,
        priority: TodoItemPriority,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id.isEmpty()) {
                todoItemsInteractor.addItem(text, priority)
            } else {
                todoItemsInteractor.updateItem(
                    TodoItem(
                        id,
                        text,
                        priority,
                        _screenState.value.isDone
                    )
                )
            }
        }
        router.exit()
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("id") id: String
        ): CreateViewModel
    }
}