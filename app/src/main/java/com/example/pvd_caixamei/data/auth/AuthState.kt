package com.example.pvd_caixamei.data.auth

sealed interface AuthState {
    data object Loading : AuthState
    data object LoggedOut : AuthState
    data object LoggedIn : AuthState
    data class Error(val message: String) : AuthState
}