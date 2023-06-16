package com.bor96dev.feature.create_impl.presentation

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    fun onExitButtonClicked() {
        router.exit()
    }
}