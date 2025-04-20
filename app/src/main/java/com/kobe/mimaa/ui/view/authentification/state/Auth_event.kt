package com.kobe.mimaa.ui.view.authentification.state

/**
 * Sealed class representing the different authentication events that can occur in the application.
 *
 * This class provides a type-safe way to represent various authentication actions, such as signing up, logging in, and logging out.
 * Using a sealed class ensures that all possible authentication events are handled explicitly.
 */
sealed class Auth_event() {
    data class OnSignUp(val userEmail: String, val userPassword: String) : Auth_event()
    data class OnSignIn(val userEmail: String, val userPassword: String) : Auth_event()
    object OnLogout : Auth_event()
}