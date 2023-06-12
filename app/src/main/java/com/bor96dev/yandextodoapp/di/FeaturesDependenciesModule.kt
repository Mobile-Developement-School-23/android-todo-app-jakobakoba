package com.bor96dev.yandextodoapp.di

import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.feature.create_impl.api.CreateImpl
import com.bor96dev.feature.items_api.ItemsApi
import com.bor96dev.feature.items_impl.api.ItemsImpl
import dagger.Module

@Module
internal class FeaturesDependenciesModule {

    fun provideItemsApi(): ItemsApi = ItemsImpl()

    fun provideCreateApi(): CreateApi = CreateImpl()
}