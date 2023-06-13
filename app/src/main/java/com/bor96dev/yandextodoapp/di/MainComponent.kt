package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.FeatureDependency
import com.bor96dev.core.di.PerFeature
import com.bor96dev.yandextodoapp.MainFragment
import dagger.Component

@PerFeature
@Component(
    modules = [FeaturesDependenciesModule::class]
)
internal interface MainComponent : FeatureDependency {

    fun inject(fragment: MainFragment)
}