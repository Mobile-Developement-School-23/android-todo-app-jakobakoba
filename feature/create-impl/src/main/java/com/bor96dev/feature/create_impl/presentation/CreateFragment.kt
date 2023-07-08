package com.bor96dev.feature.create_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bor96dev.core.di.daggerViewModels
import com.bor96dev.core.di.findCoreDependencies
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.core.di.viewBinding
import com.bor96dev.feature.create_impl.di.DaggerCreateComponent
import com.bor96dev.feature.create_impl.presentation.model.getPriorityList
import com.bor96dev.yandextodoapp.core.feature.create_impl.R
import com.bor96dev.yandextodoapp.core.feature.create_impl.databinding.CreateFragmentBinding
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class CreateFragment : Fragment(R.layout.create_fragment) {

    companion object {
        private const val ARG_ID = "ARG_ID"

        fun newInstance(id: String = "") = CreateFragment().apply {
            arguments = bundleOf(
                ARG_ID to id
            )
        }
    }

    private val todoItemId: String by lazy { arguments?.getString(ARG_ID) ?: "" }

    private val binding by viewBinding(CreateFragmentBinding::bind)

    @Inject
    lateinit var viewModelProvider: CreateViewModel.Factory

    private val viewModel: CreateViewModel by daggerViewModels { viewModelProvider.create(todoItemId) }

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

        with(binding) {
            closeButton.setOnClickListener {
                viewModel.onExitButtonClicked()
            }
            saveButton.setOnClickListener {
                val text = editText.text.toString()
                val priority = getPriorityList()[spinner.selectedItemPosition].id

                viewModel.onSaveButtonClicked(text, priority)
            }
            deleteButton.apply {
                isEnabled = todoItemId.isNotEmpty()
                setOnClickListener {
                    viewModel.removeItemButtonClicked(todoItemId)
                }
            }
        }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            getPriorityList().map { requireContext().getString(it.textId) }
        )
        binding.spinner.adapter = adapter
        binding.spinner.setSelection(1)

        screenRecreate()

        viewModel.recreateScreen(todoItemId)
    }

    private fun screenRecreate() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { item ->
                with(binding) {
                    editText.setText(item.text)
                    spinner.setSelection(
                        when (item.priority) {
                            TodoItemPriority.LOW -> 0
                            TodoItemPriority.NORMAL -> 1
                            else -> 2
                        }
                    )
                }
            }
        }
    }
}