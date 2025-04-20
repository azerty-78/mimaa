package com.kobe.mimaa.ui.view.authentification.state

import com.kobe.mimaa.data.source.local.User

data class Auth_state(
    val currentUser: User? = null,
    val isLoading: Boolean = false,

    val successSignUp: Boolean = false,
    val errorSignUp: String? = null,

    val successSignIn: Boolean = false,
    val errorSignIn: String? = null,
)
