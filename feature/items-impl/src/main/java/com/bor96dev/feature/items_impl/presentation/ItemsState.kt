package com.bor96dev.feature.items_impl.presentation

import com.bor96dev.feature.items_impl.presentation.model.ItemUi

internal data class ItemsState(
    val showNonDoneTasks: Boolean = true,
    val doneDrawable: Int = android.R.drawable.btn_star,
    val doneText: String = "0 ",
    val items: List<ItemUi> = emptyList()
)