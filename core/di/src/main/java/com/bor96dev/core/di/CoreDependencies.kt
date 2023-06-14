package com.bor96dev.core.di

import androidx.fragment.app.Fragment
import com.bor96dev.core.di.navigation.RootNavigation

interface CoreDependencies {

    fun rootNavigation(): RootNavigation
}

interface CoreDependenciesProvider : FeatureDependency {

    fun coreDependencies(): CoreDependencies
}

fun Fragment.coreDependenciesProvider(): CoreDependencies {
    var dependencyProvider: Fragment? = parentFragment

    while (parentFragment !is CoreDependenciesProvider && parentFragment != null) {
        dependencyProvider = parentFragment
    }

    return (dependencyProvider as CoreDependenciesProvider).coreDependencies()
}