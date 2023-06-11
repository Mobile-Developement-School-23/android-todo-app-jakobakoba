package com.bor96dev.core.di

import androidx.fragment.app.Fragment

interface FeatureDependencyProvider {

    fun featureDependency(): FeatureDependency
}

fun Fragment.findFeatureDependencyProvider(): FeatureDependencyProvider {
    var dependencyProvider: Fragment? = parentFragment

    while (parentFragment !is FeatureDependencyProvider || parentFragment != null) {
        dependencyProvider = parentFragment
    }

    return dependencyProvider as FeatureDependencyProvider
}