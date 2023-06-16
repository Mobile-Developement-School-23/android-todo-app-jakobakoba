package com.bor96dev.feature.create_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.daggerViewModels
import com.bor96dev.core.di.findCoreDependencies
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.core.di.viewBinding
import com.bor96dev.feature.create_impl.di.DaggerCreateComponent
import com.bor96dev.feature.create_impl.presentation.model.PriorityUi
import com.bor96dev.yandextodoapp.core.feature.create_impl.R
import com.bor96dev.yandextodoapp.core.feature.create_impl.databinding.CreateFragmentBinding
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import javax.inject.Inject
import javax.inject.Provider

internal class CreateFragment : Fragment(R.layout.create_fragment) {

    private val binding by viewBinding(CreateFragmentBinding::bind)

    @Inject
    lateinit var viewModelProvider: Provider<CreateViewModel>

    private val viewModel: CreateViewModel by daggerViewModels { viewModelProvider.get() }

    override fun onAttach(context: Context) {
        DaggerCreateComponent.factory()
            .create(
                context.findCoreDependencies(),
                context.findFeatureDependencyProvider()
            )
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val priorityOptions = arrayOf(
            PriorityUi(
                TodoItemPriority.LOW,
                requireContext().getString(R.string.priority_low)
            ),
            PriorityUi(
                TodoItemPriority.NORMAL,
                requireContext().getString(R.string.priority_normal)
            ),
            PriorityUi(
                TodoItemPriority.URGENT,
                requireContext().getString(R.string.priority_urgent)
            )
        )

        with(binding) {
            closeButton.setOnClickListener {
                viewModel.onExitButtonClicked()
            }
            saveButton.setOnClickListener {
                val text = editText.text.toString()
                val priority = priorityOptions[spinner.selectedItemPosition].id

                viewModel.onSaveButtonClicked(text, priority)
            }
        }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            priorityOptions.map { it.text }
        )
        binding.spinner.adapter = adapter
        binding.spinner.setSelection(1)
    }
}