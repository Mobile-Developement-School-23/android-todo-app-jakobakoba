package com.bor96dev.yandextodoapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.yandextodoapp.di.DaggerMainComponent
import javax.inject.Inject

internal class MainFragment : Fragment() {

    @Inject
    lateinit var createApi: CreateApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        DaggerMainComponent.create().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            childFragmentManager.beginTransaction()
                .add(R.id.fragmentMain, createApi.getFragment())
                .commit()
        }
    }
}