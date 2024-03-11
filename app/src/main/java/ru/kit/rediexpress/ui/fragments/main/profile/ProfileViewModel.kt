package ru.kit.rediexpress.ui.fragments.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import ru.kit.rediexpress.domain.supabase.supabase

class ProfileViewModel : ViewModel() {

    init {
//        viewModelScope.launch {
//            val id = supabase.auth.currentUserOrNull()?.id.orEmpty()
//            val a = supabase.from("profiles")
//                .select {
//                    filter {
//                        eq("id", id)
//                    }
//                }
//                .data
//            println("$id: $a")
//        }
    }
}