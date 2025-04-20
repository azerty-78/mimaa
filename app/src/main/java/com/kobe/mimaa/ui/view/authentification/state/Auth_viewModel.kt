package com.kobe.mimaa.ui.view.authentification.state

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kobe.mimaa.data.repository.UserRepository
import com.kobe.mimaa.util.Listener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Auth_viewModel
@Inject constructor(
    private val userRep: UserRepository
) : ViewModel()
{
    val uiState = mutableStateOf(Auth_state())

    init {
        getSignedUser()
    }

    fun onEvent(event: Auth_event){
        when(event){
            is Auth_event.OnSignIn -> {
                signIn(event.userEmail, event.userPassword)
            }
            is Auth_event.OnLogout -> {
                logout()
            }
            is Auth_event.OnSignUp -> {
                signUp(event.userEmail, event.userPassword)
            }

        }
    }

    //SignIn
    fun signIn(email: String, password: String){
        viewModelScope.launch{
            userRep.signIn(email, password).collect{result->
                when(result){
                    is Listener.Loading -> {
                        uiState.value = uiState.value.copy(
                            isLoading = true,
                            successSignIn = false,
                            errorSignIn = null
                        )
                    }
                    is Listener.Success -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            successSignIn = true,
                            //currentUser = result.data
                        )
                    }
                    is Listener.Error -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            successSignIn = false,
                            errorSignIn = result.message
                        )
                    }
                }
            }
        }
    }

    //signUp
    fun signUp(email: String, password: String){
        viewModelScope.launch {
            userRep.signUp(email, password).collect{result->
                when(result){
                    Listener.Loading -> {
                        uiState.value = uiState.value.copy(
                            isLoading = true,
                            successSignUp = false,
                            errorSignUp = null
                        )
                    }
                    is Listener.Success -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            successSignUp = true,
                            //currentUser = result.data
                        )
                    }
                    is Listener.Error -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            successSignUp = false,
                            errorSignUp = result.message
                        )
                    }
                }
            }
        }
    }

    //getSignedUser
    fun getSignedUser(){
        viewModelScope.launch{
            userRep.getSignedUser().collect{result->
                when(result){
                    is Listener.Error -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            currentUser = null,
                            errorSignIn = result.message
                        )
                    }
                    is Listener.Loading -> {
                        uiState.value = uiState.value.copy(
                            isLoading = true
                        )
                    }
                    is Listener.Success -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            currentUser = result.data
                        )
                    }
                }
            }
        }
    }

    //logout
    fun logout(){
        viewModelScope.launch {
            userRep.logOut().collect{result->
                when(result){
                    is Listener.Error -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            currentUser = null,
                            errorSignIn = result.message
                        )
                    }
                    is Listener.Loading -> {
                        uiState.value = uiState.value.copy(
                            isLoading = true
                        )
                    }
                    is Listener.Success -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            currentUser = null
                        )
                    }
                }
            }
        }
    }
}