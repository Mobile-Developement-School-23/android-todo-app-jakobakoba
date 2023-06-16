package com.bor96dev.yandextodoapp

import android.app.Application
import com.bor96dev.core.di.CoreApp
import com.bor96dev.core.di.CoreDependencies
import com.bor96dev.core.di.FeatureDependency
import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.yandextodoapp.di.DaggerMainComponent
import com.bor96dev.yandextodoapp.di.MainComponent
import com.bor96dev.yandextodoapp.di.MainComponentGetter

internal class App : Application(),
    CoreApp,
    MainComponentGetter {

    private val component = DaggerMainComponent.create()

    override fun coreDependencies(): CoreDependencies = component

    override fun featureDependencyProvider(): FeatureDependencyProvider = object : FeatureDependencyProvider {
        override fun featureDependency(): FeatureDependency = component
    }
    override fun component(): MainComponent = component
}