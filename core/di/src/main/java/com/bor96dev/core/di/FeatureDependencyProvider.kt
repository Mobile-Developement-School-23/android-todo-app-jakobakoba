package com.bor96dev.core.di

import android.content.Context

interface FeatureDependencyProvider {

    fun featureDependency(): FeatureDependency
}

fun Context.findFeatureDependencyProvider(): FeatureDependencyProvider =
    (this.applicationContext as CoreApp).featureDependencyProvider()
