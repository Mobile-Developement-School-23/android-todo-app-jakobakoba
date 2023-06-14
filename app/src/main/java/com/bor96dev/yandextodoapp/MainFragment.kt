package com.bor96dev.yandextodoapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bor96dev.core.di.CoreDependencies
import com.bor96dev.core.di.CoreDependenciesProvider
import com.bor96dev.core.di.FeatureDependency
import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.core.di.navigation.RootNavigation
import com.bor96dev.feature.items_api.ItemsApi
import com.bor96dev.yandextodoapp.di.DaggerMainComponent
import javax.inject.Inject

internal class MainFragment : Fragment(),
    CoreDependenciesProvider,
    FeatureDependencyProvider {

    @Inject
    lateinit var itemsApi: ItemsApi

    @Inject
    lateinit var navigation: RootNavigation

    // TODO перенести на viewmodel
    private val component = DaggerMainComponent.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentMain, itemsApi.getFragment())
                .commitNow()
        }
    }

    override fun featureDependency(): FeatureDependency = component
    override fun coreDependencies(): CoreDependencies = component
}