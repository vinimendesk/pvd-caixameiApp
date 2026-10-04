package com.example.pvd_caixamei.ui.login

import com.example.pvd_caixamei.data.auth.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel() {
    private val _state = MutableStateFlow<AuthState>(AuthState.Loading)

    val state: StateFlow<AuthState> = _state.asStateFlow()

    init {
        setSingOut()
    }

    fun setSingOut() {
        _state.value = AuthState.LoggedOut
    }

    fun setLogin() {
        _state.value = AuthState.LoggedIn
    }
}