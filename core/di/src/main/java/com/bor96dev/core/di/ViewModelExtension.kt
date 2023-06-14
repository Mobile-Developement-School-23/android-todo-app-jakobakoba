package com.bor96dev.core.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified VM : ViewModel> Fragment.daggerViewModels(
    crossinline viewModelProducer: () -> VM
): Lazy<VM> {
    return viewModels(this, viewModelProducer)
}

inline fun <reified VM : ViewModel> viewModels(
    viewModelStoreOwner: ViewModelStoreOwner,
    crossinline viewModelProducer: () -> VM
): Lazy<VM> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <VM : ViewModel> create(modelClass: Class<VM>) = viewModelProducer() as VM
        }
        val viewModelProvider = ViewModelProvider(viewModelStoreOwner, factory)
        viewModelProvider[VM::class.java]
    }
}