package com.bor96dev.feature.database_impl

import android.content.Context
import javax.inject.Inject

internal class RevisionPref @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val DEFAULT = -1L
        private const val PREF_NAME = "revision"
    }

    fun getRevision(): Long {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        return sharedPref.getLong(PREF_NAME, DEFAULT)
    }

    fun updateRevision(revision: Long) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        with(sharedPref.edit()) {
            putLong(PREF_NAME, revision)
            apply()
        }
    }

}