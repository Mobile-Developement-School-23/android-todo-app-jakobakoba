package com.bor96dev.feature.items_impl.di

import com.bor96dev.core.di.CoreDependencies
import com.bor96dev.core.di.FeatureDependency
import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.items_impl.presentation.ItemsFragment
import com.bor96dev.feature.items_impl.presentation.ItemsViewModel
import dagger.Component

@Component(
    dependencies = [
        FeatureDependencyProvider::class,
        CoreDependencies::class
    ],
    modules = [ItemsModule::class]
)
@PerFeature
internal interface ItemsComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreDependencies: CoreDependencies,
            featureDependencyProvider: FeatureDependencyProvider,
        ): ItemsComponent
    }

    fun inject(fragment: ItemsFragment)
}