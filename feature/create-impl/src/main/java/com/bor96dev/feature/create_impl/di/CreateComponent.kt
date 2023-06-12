package com.bor96dev.feature.create_impl.di

import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.create_impl.presentation.CreateFragment
import dagger.Component

@Component(
    dependencies = [FeatureDependencyProvider::class],
    modules = [CreateModule::class]
)
@PerFeature
internal interface CreateComponent {

    @Component.Factory
    interface Builder {
        fun create(featureDependencyProvider: FeatureDependencyProvider): CreateComponent
    }

    fun inject(fragment: CreateFragment)
}