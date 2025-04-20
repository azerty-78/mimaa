package com.kobe.mimaa.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.kobe.mimaa.data.source.local.User
import com.kobe.mimaa.util.Listener
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserRepository
@Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    private suspend fun saveLoginState(isLoggedIn: Boolean) {
        dataStore.edit { preferences->
            preferences[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    fun signUp(email: String, password: String) = flow{
        //etat de chargement
        emit(Listener.Loading)

        //processus de connexion
        val authResult = suspendCancellableCoroutine<AuthResult> { next->
            val auth = Firebase.auth
            val task = auth.createUserWithEmailAndPassword(email, password)

            //gestion du succes
            task.addOnCompleteListener { taskResult->
                if(next.isCancelled) return@addOnCompleteListener
                if(taskResult.isSuccessful) next.resume(task.result!!)
            }

            //gestion de l'erreur
            task.addOnFailureListener { exception->
                if(next.isCancelled) return@addOnFailureListener
                next.resumeWithException(exception)
            }

        }

        //etat de succes
        emit(Listener.Success(authResult))
    }.catch { excep->
        emit(Listener.Error(
            e = excep as Exception,
            message = excep.message
        ))
    }

    fun signIn(email: String, password: String) = flow{
        //emission sequentielle de l'état de chargement
        //etat de chargement
        emit(Listener.Loading)


        //processus de connexion
        val authResult = suspendCancellableCoroutine<AuthResult> { next->
            val auth = Firebase.auth
            val task = auth.signInWithEmailAndPassword(email, password)

            //gestion du succes
            task.addOnCompleteListener { taskResult->
                if(next.isCancelled) return@addOnCompleteListener
                if(taskResult.isSuccessful) next.resume(task.result!!)
            }

            //gestion de l'erreur
            task.addOnFailureListener { exception->
                if(next.isCancelled) return@addOnFailureListener
                next.resumeWithException(exception)
            }

        }
        saveLoginState(true) // Sauvegarde l'état de connexion
        //etat de succes
        emit(Listener.Success(authResult))
    }.catch { excep->
        emit(Listener.Error(
            e = excep as Exception,
            message = excep.message
        ))
    }

    fun logOut() = flow {
        emit(Listener.Loading)

        val auth = Firebase.auth
        auth.signOut()

        saveLoginState(false) // Met à jour l'état de déconnexion
        emit(Listener.Success(Unit))
    }.catch { excep->
        emit(Listener.Error(
            e = excep as Exception,
            message = excep.message
        ))
    }

    fun getSignedUser() = flow {
        emit(Listener.Loading)

        val user = Firebase.auth.currentUser
        val signedUser = if(user!=null){
            User(
                id = user.uid,
                email = user.email.toString(),
                username = user.displayName.toString(),
                profilePictureUrl = user.photoUrl.toString()
            )
        }else null

        emit(Listener.Success(data = signedUser))
    }.catch { excep->
        emit(Listener.Error(
            e = excep as Exception,
            message = excep.message
        ))
    }
}

object PreferencesKeys {
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
}