package com.bor96dev.feature.items_impl.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.feature.items_impl.presentation.model.toUI
import com.bor96dev.yandextodoapp.core.feature.items_impl.R
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ItemsViewModel @Inject constructor(
    private val todoItemsInteractor: TodoItemsInteractor,
    private val router: Router,
    private val createApi: CreateApi
) : ViewModel() {

    private val _screenState = MutableStateFlow(ItemsState())
    val screenState: StateFlow<ItemsState> = _screenState

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
        }
    }

    fun onRadioButtonClicked(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemsInteractor.makeIsDone(id)
            updateItems()
        }
    }

    fun onEyeButtonClicked() {
        viewModelScope.launch {
            val state = _screenState.value

            val showNonDoneTasks = !state.showNonDoneTasks
            val drawable = if (!state.showNonDoneTasks) {
                android.R.drawable.btn_star
            } else {
                R.drawable.not_done_icon
            }

            _screenState.emit(
                _screenState.value.copy(
                    showNonDoneTasks = showNonDoneTasks,
                    doneDrawable = drawable,
                )
            )

            updateItems()
        }
    }

    private fun updateItems() {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) { todoItemsInteractor.getItems() }
            val filteredItems = if (_screenState.value.showNonDoneTasks) {
                items.filter { !it.isDone }
            } else {
                items
            }.map { it.toUI() }

            val doneText = items.filter { it.isDone }.size

            _screenState.emit(
                _screenState.value.copy(
                    items = filteredItems,
                    doneText = doneText.toString()
                )
            )
        }
    }

    fun onResume() {
        updateItems()
    }
}