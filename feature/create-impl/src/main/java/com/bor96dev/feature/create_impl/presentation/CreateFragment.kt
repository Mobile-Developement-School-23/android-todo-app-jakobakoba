package com.bor96dev.feature.create_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.daggerViewModels
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.core.di.viewBinding
import com.bor96dev.feature.create_impl.di.DaggerCreateComponent
import com.bor96dev.yandextodoapp.core.feature.create_impl.R
import com.bor96dev.yandextodoapp.core.feature.create_impl.databinding.CreateFragmentBinding
import javax.inject.Inject
import javax.inject.Provider

internal class CreateFragment : Fragment(R.layout.create_fragment) {

    private val binding by viewBinding(CreateFragmentBinding::bind)

    @Inject
    lateinit var viewModelProvider: Provider<CreateViewModel>

    val viewModel: CreateViewModel by daggerViewModels { viewModelProvider.get() }


    override fun onAttach(context: Context) {
        DaggerCreateComponent.factory()
            .create(context.findFeatureDependencyProvider())
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeButton.setOnClickListener {
            viewModel.onExitButtonClicked()
        }
        val priorityOptions = arrayOf("No", "Normal", "Urgent")
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, priorityOptions)
        binding.spinner.adapter = adapter
        binding.spinner.setSelection(1)
    }
}