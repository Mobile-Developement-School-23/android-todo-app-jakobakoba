package com.bor96dev.feature.items_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bor96dev.core.di.*
import com.bor96dev.feature.items_impl.di.DaggerItemsComponent
import com.bor96dev.feature.items_impl.presentation.adapter.ItemsAdapter
import com.bor96dev.feature.items_impl.presentation.model.toUI
import com.bor96dev.yandextodoapp.core.feature.items_impl.R
import com.bor96dev.yandextodoapp.core.feature.items_impl.databinding.ItemsFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class ItemsFragment : Fragment(R.layout.items_fragment) {

    private val binding by viewBinding(ItemsFragmentBinding::bind)

    @Inject
    lateinit var viewModelProvider: Provider<ItemsViewModel>

    private val viewModel: ItemsViewModel by daggerViewModels { viewModelProvider.get() }

    private val itemsAdapter: ItemsAdapter by lazy { ItemsAdapter() }

    override fun onAttach(context: Context) {
        DaggerItemsComponent.factory()
            .create(
                requireContext().findCoreDependencies(),
                requireContext().findFeatureDependencyProvider()
            )
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            addButton.setOnClickListener {
                viewModel.onAddButtonClicked()
            }
            recycler.apply {
                adapter = itemsAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest { list ->
                itemsAdapter.submitList(list.map { it.toUI() })
            }
        }
    }
}