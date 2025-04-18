package com.kobe.mimaa.ui.view.authentification

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var errorE by remember { mutableStateOf(false) }
    var errorP by remember { mutableStateOf(false) }
    var p_length by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Creez votre compte",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.height(50.dp))


        //email error
        if(errorE){
            Text(
                text = "Entrez votre email",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.onError
            )
        }
        TextField(
            value = email,
            onValueChange = {newValue->
                email = newValue
            },
            label = {
                Text(
                    text = stringResource(R.string.emai),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.person_filled_icn),
                    contentDescription = "email"
                )
            },
            trailingIcon = {
                if(email.isNotEmpty()){
                    Icon(
                        painter = painterResource(R.drawable.close_icn),
                        contentDescription = null,
                        modifier = Modifier.clickable { email = empty }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Red,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.White
            ),
        )


        Spacer(modifier = Modifier.height(10.dp))
        //password error
        if(errorP){
            Text(
                text = "Entrez votre mot de passe",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.onError
            )
        }
        if(p_length){
            Text(
                text = "Le mot de passe doit avoir au moins 6 caracteres",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.onError
            )
        }

        //password textfield
        TextField(
            value = password,
            onValueChange = {newValue->
                password = newValue
                p_length = (newValue.length < 6)
            },
            label = { Text(text = stringResource(R.string.password),) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.lock_filled_icn),
                    contentDescription = "password"
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
                    Icon(
                        painter = visibilityIcon,
                        contentDescription = if(passwordVisibility) "hide Password" else "Show Password",
                        modifier = Modifier
                            .clickable {
                                passwordVisibility =! passwordVisibility
                            }
                    )
                }
            },
            visualTransformation = if(passwordVisibility){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Red,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.White
            ),
        )

        Spacer(modifier = Modifier.height(50.dp))

        //Button de connexion
        Button(
            onClick = {
                //conditions
                if(email.isNotEmpty()){
                    errorE = false
                    if(password.isNotEmpty()){
                        errorP = false
                        //code de connexion
                    }else{
                        errorP = true
                    }
                }else{
                    errorE = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
        ){
            Text(
                text = "Se connecter",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

        //Ou
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Ou",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))

        //continuer avec google
        Button(
            onClick = {
                //
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.home_filled_icn),
                    contentDescription = "Google logo",
                    modifier = Modifier.size(30.dp),
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

        Spacer(modifier = Modifier.weight(1f))
        //navigation
        Text(
            text = "Vous n'avez pas de compte ? Creez un compte",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.Screen.SingUpScreen.route)
                }
            ,
            //color = androidx.compose.material3.MaterialTheme.colorScheme.onError
        )
        Spacer(modifier = Modifier.height(50.dp))


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
    
}