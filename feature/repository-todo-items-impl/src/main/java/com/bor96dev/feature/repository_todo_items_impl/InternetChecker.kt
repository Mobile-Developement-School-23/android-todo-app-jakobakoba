package com.bor96dev.feature.repository_todo_items_impl

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


internal class InternetChecker @Inject constructor(
    private val context: Context
) {

    // https://stackoverflow.com/questions/9570237/android-check-internet-connection
    fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}