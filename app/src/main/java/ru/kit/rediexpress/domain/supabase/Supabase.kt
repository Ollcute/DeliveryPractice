package ru.kit.rediexpress.domain.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.ExternalAuthAction
import io.github.jan.supabase.postgrest.Postgrest

private const val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRod3p4a2FqY3Fscmt2dG9xaXVrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg5MzQ0OTEsImV4cCI6MjAyNDUxMDQ5MX0.8k6uBTbNsW-GBmFvacD_8_P1m4Z1F4Q7RKZzteMrz-w"

val supabase = createSupabaseClient(
    supabaseUrl = "https://thwzxkajcqlrkvtoqiuk.supabase.co",
    supabaseKey = API_KEY
) {
    install(Postgrest)
    install(Auth) {
        host = "ru.kit.rediexpress"
        scheme = "deeplink scheme"
        defaultExternalAuthAction = ExternalAuthAction.CUSTOM_TABS
    }
}