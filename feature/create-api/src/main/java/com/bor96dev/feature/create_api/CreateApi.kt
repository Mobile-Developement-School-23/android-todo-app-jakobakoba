package com.bor96dev.feature.create_api

import androidx.fragment.app.Fragment

interface CreateApi {
    fun getFragment(id: String = ""): Fragment
}