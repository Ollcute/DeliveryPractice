package ru.kit.rediexpress.domain.models


data class InsertProfileModel(
    val full_name: String,
    val phone_number: String,
    val email_address: String,
    val avatar_file: String = "default",
)