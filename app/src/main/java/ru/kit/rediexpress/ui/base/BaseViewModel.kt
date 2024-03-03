package ru.kit.rediexpress.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    private val _baseEvent = MutableSharedFlow<BaseEvent>()
    val baseEvent: SharedFlow<BaseEvent>
        get() = _baseEvent.asSharedFlow()


    private fun emitBaseEvent(event: BaseEvent) = viewModelScope.launch {
        _baseEvent.emit(event)
    }

    protected fun onError(message: String) {
        emitBaseEvent(
            event = BaseEvent.OnError(message = message)
        )
    }
}