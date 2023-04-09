package com.example.varorussell.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel: ViewModel() {

    protected fun <T> StateFlow<T>.tryToEmit(data: T) {
        (this as MutableStateFlow).tryEmit(data)
    }
}