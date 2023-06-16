package com.bor96dev.feature.create_impl.api

import androidx.fragment.app.Fragment
import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.feature.create_impl.presentation.CreateFragment

class CreateImpl : CreateApi {

    override fun getFragment(id: String): Fragment = CreateFragment.newInstance(id)
}