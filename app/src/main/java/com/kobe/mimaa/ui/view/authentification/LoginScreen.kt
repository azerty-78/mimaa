package com.kobe.mimaa.ui.view.authentification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kobe.mimaa.R
import com.kobe.mimaa.presentation.navgraph.Routes

@Composable
fun LoginScreen(
    navController: NavController
) {
    val empty by remember { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    val passwordValidation = remember(password) { validatePassword(password) }

    var isPasswordFocused by remember { mutableStateOf(false) }
    val showPasswordRequirements = password.isNotEmpty() || isPasswordFocused
    var loginAttempted by remember { mutableStateOf(false) }

    var validations = remember(email, password){
        mapOf(
            "email" to isValidEmail(email),
            "password" to passwordValidation.isValid,
            "emailEmpty" to email.isEmpty(),
            "passwordEmpty" to password.isEmpty(),
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(100.dp))
            //logo App
            Image(
                painter = painterResource(id = R.drawable.person_filled_icn),
                contentDescription = "logo app",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(30.dp))
                ,
            )
            Text(
                text = "Connectez-vous",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(50.dp))


            //email error
            OutlinedTextField(
                value = email,
                onValueChange = { newValue ->
                    email = newValue
                },
                label = { Text(
                    text = stringResource(R.string.emai),
                    //color = MaterialTheme.colorScheme.onSurfaceVariant
                ) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.person_filled_icn),
                        contentDescription = "email",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    if (email.isNotEmpty()) {
                        IconButton(onClick = { email = empty }) {
                            Icon(
                                painter = painterResource(R.drawable.close_icn),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                    }
                },
                isError = email.isNotBlank() && !validations["email"]!!,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                textStyle = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                ,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    //focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    //focusedLabelColor = MaterialTheme.colorScheme.primary, // Label focus
                    cursorColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onSurface // Texte saisi
                ),
            )
            if(!validations["email"]!! && email.isNotBlank()) {
                Text(
                    text = when {
                        email.count { it == '@' } > 1 -> "Trop de @ dans l'email"
                        '@' !in email -> "L'email doit contenir @"
                        email.split("@").let { it.size != 2 || it[1].count { c -> c == '.' } < 1 } ->
                            "Format de domaine invalide (ex: exemple.com)"
                        email.startsWith('.') || email.endsWith('.') ->
                            "Ne peut pas commencer/finir par un point"
                        else -> "Format d'email invalide"
                    },
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
            }else if (validations["emailEmpty"]!!)
            {
                Text(
                    text = "Email requis",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))


            //password
            OutlinedTextField(
                value = password,
                onValueChange = {newValue->
                    password = newValue
                },

                label = {
                    Text(
                        text = stringResource(R.string.password),
                        //color = MaterialTheme.colorScheme.onSurfaceVariant
                    ) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.lock_filled_icn),
                        contentDescription = "password",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    if(password.isNotEmpty()){
                        val visibilityIcon =
                            if(passwordVisibility){
                                painterResource(R.drawable.visibillity_on_filled_icn)
                            }
                            else{ painterResource(R.drawable.visibility_off_filled_icn)
                            }
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }){
                            Icon(
                                painter = visibilityIcon,
                                contentDescription = if(passwordVisibility) "hide Password" else "Show Password",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                visualTransformation = if(passwordVisibility){
                    VisualTransformation.None
                }
                else{
                    PasswordVisualTransformation()
                },
                isError = password.isNotBlank() && !validations["password"]!!,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                textStyle = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .onFocusChanged { focuseState ->
                        isPasswordFocused = focuseState.isFocused
                    }
                ,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    //focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant, // Label non focus
                    //focusedLabelColor = MaterialTheme.colorScheme.primary, // Label focus
                    cursorColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onSurface // Texte saisi
                ),
            )
            AnimatedPasswordRequirements(
                password = password,
                showRequirements = showPasswordRequirements,
                modifier = Modifier.fillMaxWidth()
            )
            // Message d'erreur global seulement après tentative de soumission
            if ((loginAttempted && !passwordValidation.isValid) || validations["passwordEmpty"]!!) {
                Text(
                    text = "Mot de passe obligatiore",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            //navigation
            TextButton(
                onClick = { navController.navigate(Routes.Screen.ForgotPasswordScreen.route) },
            ){
                Text(
                    text = "Mot de passe oublie ? Changer le.",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            //Button de connexion
            Button(
                onClick = {
                    loginAttempted = true
                    if (passwordValidation.isValid && isValidEmail(email)) {
                        // Procéder à la connexion
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevation(10.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                enabled = passwordValidation.isValid && isValidEmail(email),
            ){
                Text(
                    text = "Se connecter",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }


            // Divider
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
                Text(
                    text = "OU",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MaterialTheme.colorScheme.outline
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )
            }

            //continuer avec google
            OutlinedButton(
                onClick = {
                    /* Google login */
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledBackgroundColor = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 2f
                    )
                ),
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevation(10.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.google_icn),
                        contentDescription = "Google logo",
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Continuer avec Google",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            //navigation
            TextButton(
                onClick = { navController.navigate(Routes.Screen.SingUpScreen.route) },
            ){
                Text(
                    text = "Vous n'avez pas de compte ? Creez un compte",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "© 2023 Mimaa - Tous droits réservés",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                ),
            )
            Spacer(modifier = Modifier.height(50.dp))

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)

}