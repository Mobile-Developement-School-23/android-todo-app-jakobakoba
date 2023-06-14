package com.bor96dev.feature.items_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.feature.items_impl.di.DaggerItemsComponent
import com.bor96dev.yandextodoapp.core.feature.items_impl.R

internal class ItemsFragment : Fragment() {

    override fun onAttach(context: Context) {
        DaggerItemsComponent.factory()
            .create(findFeatureDependencyProvider())
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.items_fragment, container, false)
    }
}