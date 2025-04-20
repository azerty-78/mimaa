package com.kobe.mimaa



import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.kobe.mimaa.presentation.navgraph.Routes
import com.kobe.mimaa.ui.theme.MimaaTheme
import com.kobe.mimaa.ui.view.authentification.state.Auth_viewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MimaaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: Auth_viewModel = hiltViewModel()

                    // Observer l'état d'authentification
                    LaunchedEffect(authViewModel.uiState.value.currentUser) {
                        if (authViewModel.uiState.value.currentUser != null) {
                            navController.navigate(Routes.HOME_GRAPHROUTE) {
                                popUpTo(Routes.AUTH_GRAPHROUTE) { inclusive = true }
                            }
                        }
                    }

                    MainScreen(navController = navController)
                }
            }
        }
    }
}
