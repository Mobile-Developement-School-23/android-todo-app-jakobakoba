package com.bor96dev.feature.create_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.findFeatureDependencyProvider
import com.bor96dev.feature.create_impl.di.DaggerCreateComponent
import com.bor96dev.yandextodoapp.core.feature.create_impl.databinding.CreateFragmentBinding

internal class CreateFragment : Fragment() {

    private var _binding: CreateFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        DaggerCreateComponent.factory()
            .create(findFeatureDependencyProvider())
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        _binding = CreateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeButton.setOnClickListener { }
        binding.editText
        binding.deleteButton
    }

}