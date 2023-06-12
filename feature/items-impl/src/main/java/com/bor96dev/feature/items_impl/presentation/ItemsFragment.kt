package com.bor96dev.feature.items_impl.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.findFeatureDependencyProvider

internal class ItemsFragment : Fragment() {

    override fun onAttach(context: Context) {
//        DaggerItemsComponent.factory()
//            .create(findFeatureDependencyProvider())
//            .inject(this)
        super.onAttach(context)
    }
}