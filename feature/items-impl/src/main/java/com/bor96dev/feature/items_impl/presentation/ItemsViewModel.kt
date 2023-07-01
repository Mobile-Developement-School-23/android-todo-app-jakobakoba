package com.bor96dev.feature.items_impl.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.feature.items_impl.presentation.model.ItemUi
import com.bor96dev.feature.items_impl.presentation.model.toUI
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ItemsViewModel @Inject constructor(
    private val todoItemsInteractor: TodoItemsInteractor,
    private val router: Router,
    private val createApi: CreateApi
) : ViewModel() {

    private val _screenState = MutableStateFlow(ItemsState())
    private val screenState: StateFlow<ItemsState> = _screenState

    private val _todos = MutableStateFlow(emptyList<ItemUi>())
    val todos: StateFlow<List<ItemUi>> = _todos

    private val _doneItemsCount = MutableStateFlow(0)
    val doneItemsCount: StateFlow<Int> = _doneItemsCount

    private val _showNonDoneTasks = MutableStateFlow(true)
    val showNonDoneTasks: StateFlow<Boolean> = _showNonDoneTasks.asStateFlow()

    init {
      updateItems()
    }

    fun onAddButtonClicked() {
        router.navigateTo(object : FragmentScreen {
            override fun createFragment(factory: FragmentFactory): Fragment =
                createApi.getFragment()
        })
    }

    fun onItemClicked(id: String) {
        router.navigateTo(object : FragmentScreen {
            override fun createFragment(factory: FragmentFactory): Fragment =
                createApi.getFragment(id)
        })
    }

    fun removeItemButtonClicked(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemsInteractor.removeItem(id)
            updateItems()
        }
    }

    fun onRadioButtonClicked(id: String, isDone: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemsInteractor.makeIsDone(id, isDone)
            updateItems()
        }
    }

    fun onEyeButtonClicked() {
        viewModelScope.launch {
            _showNonDoneTasks.emit(
                !_showNonDoneTasks.value
            )
        }
    }

    private fun updateItems() {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) { todoItemsInteractor.getItems() }
            val filteredItems = if (showNonDoneTasks.value) {

                items
            } else {
                items

            }
            Log.d("GTA5", "items = $items \n filtered items = $filteredItems")

            _todos.emit(filteredItems.map { it.toUI() })
            _doneItemsCount.emit(items.count { it.isDone })
        }
    }

    fun onResume() {
        updateItems()
    }
}