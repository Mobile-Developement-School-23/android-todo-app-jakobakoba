package com.bor96dev.feature.items_impl.api

import androidx.fragment.app.Fragment
import com.bor96dev.feature.items_api.ItemsApi
import com.bor96dev.feature.items_impl.presentation.ItemsFragment

class ItemsImpl : ItemsApi {
    override fun getFragment(): Fragment = ItemsFragment()
}