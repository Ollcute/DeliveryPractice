package ru.kit.rediexpress.ui.base

sealed class BaseEvent {
    data class OnError(val message: String): BaseEvent()
}
