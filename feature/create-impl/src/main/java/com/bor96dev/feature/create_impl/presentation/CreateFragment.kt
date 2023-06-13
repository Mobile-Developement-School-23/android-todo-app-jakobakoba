package com.bor96dev.feature.create_impl.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.feature.create_impl.di.DaggerCreateComponent

internal class CreateFragment : Fragment() {

    override fun onAttach(context: Context) {
        DaggerCreateComponent.factory()
            .create(findFeatureDependencyProvider())
            .inject(this)
        super.onAttach(context)
    }
}