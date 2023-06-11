package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.FeatureDependency
import dagger.Component

@Component(
    modules = [FeaturesDependenciesModule::class]
)
internal interface MainComponent : FeatureDependency {


}