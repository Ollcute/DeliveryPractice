package ru.kit.rediexpress.domain


data class SelectTransactionsModel(
    val id: Long,
    val profile: Long,
    val amoun: Float,
    val comment: String
)