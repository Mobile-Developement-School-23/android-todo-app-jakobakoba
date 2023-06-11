package com.bor96dev.feature.items_impl.di

import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.items_impl.presentation.ItemsFragment
import dagger.Component

@Component(
    dependencies = [FeatureDependencyProvider::class],
    modules = [ItemsModule::class]
)
@PerFeature
internal interface ItemsComponent {

    @Component.Factory
    interface Builder {
        fun create(featureDependencyProvider: FeatureDependencyProvider): ItemsComponent
    }

    fun inject(fragment: ItemsFragment)
}