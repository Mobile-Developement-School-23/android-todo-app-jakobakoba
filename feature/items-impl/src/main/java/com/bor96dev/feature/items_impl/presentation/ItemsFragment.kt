package com.bor96dev.feature.items_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bor96dev.core.di.daggerViewModels
import com.bor96dev.core.di.findCoreDependencies
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.core.di.viewBinding
import com.bor96dev.feature.items_impl.di.DaggerItemsComponent
import com.bor96dev.feature.items_impl.presentation.adapter.ItemsAdapter
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

    private val itemsAdapter: ItemsAdapter by lazy {
        ItemsAdapter(
            requireContext(),
            { viewModel.onItemClicked(it) },
            { viewModel.onRadioButtonClicked(it) },
            { viewModel.removeItemButtonClicked(it) }
        )
    }

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
                itemsAdapter.attachRecyclerView(this)
                layoutManager = LinearLayoutManager(requireContext())


                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy > 0 || dy < 0 && addButton.isShown) {
                            addButton.hide()
                        }
                    }

                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            addButton.show()
                        }
                        super.onScrollStateChanged(recyclerView, newState)
                    }
                })
            }
            eyes.setOnClickListener {
                viewModel.onEyeButtonClicked()
            }
        }

        render()
    }

    private fun render() {
        lifecycleScope.launch {
            viewModel.screenState.collectLatest { state ->
                binding.eyes.setBackgroundResource(state.doneDrawable)
                binding.textNumber.text = state.doneText
                itemsAdapter.submitList(state.items)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}


