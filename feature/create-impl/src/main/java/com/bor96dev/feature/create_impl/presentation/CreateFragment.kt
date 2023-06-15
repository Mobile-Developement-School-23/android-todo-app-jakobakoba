package com.bor96dev.feature.create_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.core.di.viewBinding
import com.bor96dev.feature.create_impl.di.DaggerCreateComponent
import com.bor96dev.yandextodoapp.core.feature.create_impl.R
import com.bor96dev.yandextodoapp.core.feature.create_impl.databinding.CreateFragmentBinding

internal class CreateFragment : Fragment(R.layout.create_fragment) {

    private val binding by viewBinding(CreateFragmentBinding::bind)


    override fun onAttach(context: Context) {
        DaggerCreateComponent.factory()
            .create(context.findFeatureDependencyProvider())
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeButton.setOnClickListener { }
        binding.editText
        binding.deleteButton
    }

}