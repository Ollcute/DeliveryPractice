package presentation.utils.impl

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import presentation.utils.EventProvider

class EventProviderImpl<T>: EventProvider<T> {
    override val event: SharedFlow<T>
        get() = _event

    private val _event = MutableSharedFlow<T>()

    override suspend fun emitEvent(event: T) {
        _event.emit(event)
    }
}