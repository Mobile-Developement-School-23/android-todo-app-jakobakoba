package com.bor96dev.core.di

interface CoreApp {
    fun coreDependencies(): CoreDependencies
    fun featureDependencyProvider(): FeatureDependencyProvider
}