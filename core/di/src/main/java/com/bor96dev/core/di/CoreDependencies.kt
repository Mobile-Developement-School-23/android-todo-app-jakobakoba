package com.bor96dev.core.di

import android.content.Context
import com.github.terrakok.cicerone.Router

interface CoreDependencies {

    fun router(): Router
}

fun Context.findCoreDependencies(): CoreDependencies =
    (this.applicationContext as CoreApp).coreDependencies()
